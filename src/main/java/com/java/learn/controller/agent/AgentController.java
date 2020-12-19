package com.java.learn.controller.agent;

import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/agent")
public class AgentController {
    private static Map<String, VirtualMachineDescriptor> jvmMap=null;

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
            int i = displayName.indexOf("/");
            if (i > 0) {
                displayName = displayName.substring(0, i);
            }
            map.put(pid, displayName.trim());
        }
        return Mono.just(map);
    }

    @PostMapping("/attachPid")
    public Mono<Object> attachPid(@PathVariable(name = "pId") String pId, MultipartFile multipartFile) {
        if (jvmMap.containsKey(pId)) {
            try {
                VirtualMachine attach = VirtualMachine.attach(pId);
                //输出到类路径，再从类路径获取
                attach.loadAgent("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, String> jvmMap = new HashMap<>();

        return Mono.just(jvmMap);
    }
}
