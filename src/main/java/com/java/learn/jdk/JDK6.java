package com.java.learn.jdk;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.core.io.ClassPathResource;

import javax.imageio.ImageIO;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.swing.*;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.*;
import java.util.List;

public class JDK6 {
    public static void main(String[] args) throws Exception {
        //awtTest();
        //jaxB2();
        //httpServerApi();
        //testSTAX();
        //compileApi();
        //javaSciptTest();
        
    }



    /**
     * JDK 6在java.awt包下，新增了两个类：Desktop类和SystemTray类
     * Desktop类: 用来打开系统默认浏览器浏览指定的URL,打开系统默认邮件客户端发邮件等
     * SystemTray类:用来在系统托盘区创建一个托盘程序,如果在微软的Windows上，它被称为“任务栏”状态区域。
     * @throws Exception
     */
    public static void awtTest() throws Exception {
        SystemTray systemTray = SystemTray.getSystemTray();
        systemTray.add(getTrayIcon());
    }

    private static TrayIcon getTrayIcon() throws IOException {
        Image image = getImage();
        TrayIcon trayIcon = new TrayIcon(image, "Test", getPopupMenu());
        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openJframe(image);
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.browse(URI.create("https://www.baidu.com"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        return trayIcon;
    }

    private static void openJframe(Image image) {
        JFrame jFrame = new JFrame();
        center(jFrame);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setAlwaysOnTop(true);
        jFrame.setSize(450,450);
        jFrame.setIconImage(image);
        jFrame.setAutoRequestFocus(true);
    }

    //窗口居中
    private static void center(JFrame jFrame)
    {
        //获得当前屏幕的尺寸
      Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
      //获得JFrame尺寸
      Dimension jframeSize=jFrame.getSize();
      jFrame.setLocation((screenSize.width-jframeSize.width)/2,(screenSize.height-jframeSize.height)/2);
    }
    private static Image getImage() throws IOException {
        return ImageIO.read(JDK6.class.getClassLoader().getResource("effective java.jpg"));
    }

    private static PopupMenu getPopupMenu() {
        PopupMenu popupMenu = new PopupMenu();
        MenuItem close = new MenuItem("close");
        close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==close)
                {
                    System.exit(-1);
                }
            }
        });
        popupMenu.add(close);
        return popupMenu;
    }

    /**
     * 使用JAXB2来实现对象与XML之间的映射
     * JAXB,即Java Architecture for XML Binding,可以实现对象与XML之间的映射，常用注解如下:
     * @XmlRootElement：注解在类上面，对应xml的跟元素，使用name属性定义根节点的名称。
     * @XmlElement：指定一个字段或get/set方法映射到xml的节点，使用name属性定义这个根节点的名称。
     * @XmlAttribute：将JavaBean对象的属性映射为xml的属性,使用name属性为生成的xml属性指定别名。
     * @XmlAccessorType:定义映射这个类中的何种类型都需要映射到xml。
     * @XmlSchema: 将包映射到XML名称空间
     */
    public static void jaxB2() throws JAXBException {
        JAXCLass jaxcLass = new JAXCLass();
        jaxcLass.setAttribute(jaxcLass.getClass().getName());
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        Map<String, String> map = new HashMap<>();
        map.put("1", "map");
        map.put("2", "map");
        jaxcLass.setLists(list);
        jaxcLass.setMap(map);
        System.out.println(beanToxml(jaxcLass, JAXCLass.class));
    }

    /**
     * STAX，是JDK6中一种处理XML文档的API。
     */
    public static void testSTAX() throws IOException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(new FileInputStream("E:\\workspace\\javaLearn\\src\\main\\resources\\test.xml"));
        XMLEvent event = null;
        StringBuilder stringBuilder = new StringBuilder();
        while (xmlEventReader.hasNext()) {
            System.out.println(xmlEventReader.nextEvent().toString());
        }
    }
    private static <T> String beanToxml(T obj, Class<T> clazz) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(clazz);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
        StringWriter writer = new StringWriter();
        marshaller.marshal(obj, writer);
        return writer.toString();
    }
    @XmlRootElement(name="JAX")
    static class JAXCLass {
        private String attribute;
        private Map<String,String> map;
        private List<String> lists;

        @XmlAttribute(name = "class")
        public String getAttribute() {
            return attribute;
        }

        public void setAttribute(String attribute) {
            this.attribute = attribute;
        }

        @XmlElement(name = "map")
        public Map<String, String> getMap() {
            return map;
        }

        public void setMap(Map<String, String> map) {
            this.map = map;
        }
        @XmlElement(name = "list")
        public List<String> getLists() {
            return lists;
        }

        public void setLists(List<String> lists) {
            this.lists = lists;
        }
    }

    /**
     * 轻量级 Http Server API
     * JDK 6中提供了简单的Http Server API，可以构建嵌入式Http服务器,同时支持Http和Https协议。
     * HttpServer会调用HttpHandler实现类的回调方法来处理客户端请求,这里用户只需实现HttpHandler接口就可以了。
     */
    public static void httpServerApi() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 10);
        httpServer.createContext("/", new MyHttpHandler());
        httpServer.start();

    }

    static class MyHttpHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            //请求头
            Headers requestHeaders = httpExchange.getRequestHeaders();
            Set<Map.Entry<String, List<String>>> entries = requestHeaders.entrySet();
            StringBuffer stringBuffer = new StringBuffer();
            for (Map.Entry<String, List<String>> entry : entries) {
                stringBuffer.append(entry.toString())
                        .append("\n");
            }
            //设置响应头属性及响应信息的长度
            httpExchange.sendResponseHeaders(200, stringBuffer.length());
            //获得输出流
            OutputStream responseBody = httpExchange.getResponseBody();
            responseBody.write(stringBuffer.toString().getBytes());
            responseBody.close();
        }
    }

    /**
     *  插入式注解处理API
     *
     * JDK 6提供了插入式注解处理API，可以让我们定义的注解在编译期而不是运行期生效，从而可以在编译期修改字节码。
     * lombok框架就是使用该特性来实现的，Lombok通过注解的方式，在编译时自动为属性生成构造器、getter/setter、equals、hashcode、toString等方法，大大简化了代码的开发。
     */
    public static void annotationProcessor(){
    }

    /**
     * Common annotations原本是Java EE 5.0(JSR 244)规范的一部分，现在SUN把它的一部分放到了Java SE 6.0中。
     * 随着Annotation元数据功能加入到Java SE 5.0里面，很多Java 技术都会用Annotation部分代替XML文件来配置运行参数。
     */
    public static void newAnnotation(){
        //@Generated：用于标注生成的源代码
        //@Resource： 用于标注所依赖的资源，容器据此注入外部资源依赖，有基于字段的注入和基于setter方法的注入两种方式 。
        //@Resources：同时标注多个外部依赖，容器会把所有这些外部依赖注入
        //@PostConstruct：标注当容器注入所有依赖之后运行的方法，用来进行依赖注入后的初始化工作，只有一个方法可以标注为PostConstruct 。
        //@PreDestroy：当对象实例将要被从容器当中删掉之前，要执行的回调方法要标注为PreDestroy
    }

    /**
     * javac编译器可以把.java的源文件编译为.class文件，JDK 6的新特性Compiler API(JSR 199)也可以动态编译Java源文件。
     * @throws IOException
     */
    public static void compileApi() throws IOException {
        JavaCompiler systemJavaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager manager = systemJavaCompiler.getStandardFileManager(null, null, null);
        Iterable<? extends JavaFileObject> javaFileObjects = manager.getJavaFileObjects("E:\\workspace\\javaLearn\\src\\main\\java\\com\\java\\learn\\jdk\\JDK5.java");
        JavaCompiler.CompilationTask task = systemJavaCompiler.getTask(null, manager, null, null, null, javaFileObjects);
         task.call();
         manager.close();
    }
    public static void javaSciptTest() throws ScriptException {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine javaScript = scriptEngineManager.getEngineByName("JavaScript");
        javaScript.eval("print('Hello world')");
        javaScript.eval("print(10)");
    }
}
