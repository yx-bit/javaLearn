package com.java.learn.controller.agent;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.codec.multipart.FormFieldPart;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import sun.tools.java.ClassPath;

import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/agent")
public class AgentController {
    private static Map<String, VirtualMachineDescriptor> jvmMap = null;

    @Autowired
    ResourceLoader resourceLoader;
    @GetMapping("/getJvms")
    public Mono<Object> getJvms() {
        jvmMap = new ConcurrentHashMap<>();
        Map<String, String> map = new HashMap<>();
        //查找所有jvm进程，排除attach测试工程
        List<VirtualMachineDescriptor> attach = VirtualMachine.list();
        for (VirtualMachineDescriptor entry : attach) {
            String pid = entry.id();
            jvmMap.put(pid, entry);
            String displayName = entry.displayName();
            int i = displayName.indexOf(" ");
            if (i > 0) {
                displayName = displayName.substring(0, i);
            }
            if(StringUtils.isEmpty(displayName)){
                continue;
            }
            map.put(pid, displayName.trim());
        }
        return Mono.just(map);
    }

    @PostMapping(value = "/attachPid", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<Object> attachPid(ServerWebExchange serverWebExchange, @RequestPart("pId") FormFieldPart fieldPart, @RequestPart("multipartFile") FilePart multipartFile) throws IOException {
        String pId = fieldPart.value();
        VirtualMachine virtualmachine=null;
        try {
            //输出到类路径，再从类路径获取
            Resource resource = resourceLoader.getResource("classpath:");
            String pathStr = resource.getURI().getPath().replaceFirst("/", "")+ multipartFile.filename();
            Path path = Paths.get(pathStr );
            boolean exists = Files.exists(path, LinkOption.NOFOLLOW_LINKS);
            if (exists) {
                Files.delete(path);
            }
            Path file = Files.createFile(path);
            multipartFile.transferTo(file);
            // Attach到被监控的JVM进程上
            virtualmachine = VirtualMachine.attach(pId);
            // 让JVM加载jmx Agent
            virtualmachine.loadAgent(pathStr, "com.sun.management.jmxremote");
            // 获得连接地址
            Properties properties = virtualmachine.getAgentProperties();
            String address = (String) properties.get("com.sun.management.jmxremote.localConnectorAddress");
            // 通过jxm address来获取RuntimeMXBean对象，从而得到虚拟机运行时相关信息
            JMXServiceURL url = new JMXServiceURL(address);
            JMXConnector connector = JMXConnectorFactory.connect(url);
            RuntimeMXBean rmxb = ManagementFactory.newPlatformMXBeanProxy(connector.getMBeanServerConnection(), "java.lang:type=Runtime",
                    RuntimeMXBean.class);
            // 得到目标虚拟机占用cpu时间
            System.out.println(rmxb.getUptime());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            assert virtualmachine != null;
            virtualmachine.detach();
        }

        return Mono.just("Attach成功");
    }
}
