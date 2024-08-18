package com.java.learn.mybatis;

import org.junit.Before;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MybatisGenerator {
    private File configFile;
    private Properties properties;
    @Before
    public void before() throws IOException {
        //读取mybatis参数
        configFile = new File("E:\\workspace\\javaLearn\\src\\main\\resources\\generatorConfig.xml");
        properties = new Properties();
        properties.load(new FileInputStream(new File("E:\\workspace\\javaLearn\\src\\main\\resources\\datasource.properties")));
    }

    @Test
    public void test() throws Exception {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(properties,warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
