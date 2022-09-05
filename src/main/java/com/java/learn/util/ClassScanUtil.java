package com.java.learn.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassScanUtil {

    public static void main(String[] args) throws Exception {
        List<URL> urls = new ArrayList<>();
        new ClassScanUtil().scan(urls);
        Set<String> classes = scanUrls(urls);
        System.out.println(classes);
    }

    private static Set<String> scanUrls(List<URL> urls) throws URISyntaxException, IOException {
        Set<String> classes = new HashSet<>();
        for (URL url : urls) {
            if (!url.toURI().getScheme().equals("file")) {
                continue;
            }
            File file = new File(url.toURI());
            if (!file.exists()) {
                continue;
            }
            if (file.isDirectory()) {
                Files.walkFileTree(file.toPath(), new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
                        File toFile = path.toFile();
                        if (toFile.getName().endsWith(".class")) {
                            String substring = toFile.getAbsolutePath().substring(file.getAbsolutePath().length());
                            if (substring.startsWith(File.separator)) {
                                substring = substring.substring(1);
                            }
                            substring = substring.replace(".class", "");
                            classes.add(substring.replace(File.separator, "."));
                        }
                        return super.visitFile(path, attrs);
                    }
                });
            }
            if (file.getName().endsWith(".jar")) {
                JarFile jarFile = new JarFile(file);
                Enumeration<JarEntry> entries = jarFile.entries();
                while (entries.hasMoreElements()) {
                    JarEntry jarEntry = entries.nextElement();
                    if (jarEntry.getName().endsWith(".class")) {
                        String replace = jarEntry.getName().replace("/", ".");
                        String className = replace.replace(".class", "");
                        classes.add(className);
                    }
                }
            }

        }
        return classes;
    }

    public void scan(List<URL> urls) throws MalformedURLException {
        if (this.getClass().getClassLoader() instanceof URLClassLoader) {
            Collections.addAll(urls, ((URLClassLoader) this.getClass().getClassLoader()).getURLs());
            return;
        }
        String classPath = System.getProperty("java.class.path");
        String[] classPaths = classPath.split(";");
        for (String path : classPaths) {
            urls.add(new File(path).toURI().toURL());
        }
    }
}
