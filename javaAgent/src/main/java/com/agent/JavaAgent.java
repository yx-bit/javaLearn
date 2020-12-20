package com.agent;

import com.agent.interceptor.TimeInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class JavaAgent {
    private  static Logger logger = LoggerFactory.getLogger(JavaAgent.class);
        //java agent 入口
        public static void premain(String agentOps, Instrumentation inst) {
            logger.info("=========premain方法执行========");
            soutClass(agentOps,inst);
        }
        public static void agentmain(String agentOps, Instrumentation inst) {
            logger.info("=========agentmain方法执行========");
            //soutClass(agentOps,inst);
            buttyBuddyDemo(agentOps,inst);
            //transform是会对尚未加载的类进行增加代理层，这里是已经运行中的jvm，所以类以及被加载了
            //必须主动调用retransformClasses让jvm再对运行中的类进行加上代理层
        }
       /* public static void simpleDemo(String agentOps, Instrumentation inst) {
            inst.addTransformer(new ClassFileTransformer() {
                @Override
                public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                    //判断是指定的class
                    if ("com/example/agentdemoweb/Test".equals(className)) {
                        try {
                            //获取更改后的类class 字节数组
                            String path="/Users/zhangchanglu/IdeaProjects/agent-example/agent-demo-web/src/main/resources/Test.class";
                            classfileBuffer = Files.readAllBytes(Paths.get(path));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return classfileBuffer;
                }
            },true);
        }*/



    /**
     * 打印已经加载的class
     * @param agentOps
     * @param inst
     */
    public static void soutClass(String agentOps, Instrumentation inst){
        inst.addTransformer(new ClassFileTransformer() {
            @Override
            public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
                logger.info(className);
                return classfileBuffer;
            }
        });
    }
    /**
     * 使用bytebuddy来实现类的增强
     * @param agentOps
     * @param inst
     */
    public static void buttyBuddyDemo(String agentOps, Instrumentation inst) {
        new AgentBuilder.Default()
                .type(ElementMatchers.any())
                .transform((builder, typeDescription, classLoader, javaModule) ->
                        builder
                                .method(ElementMatchers.any())//test 方法
                                .intercept(MethodDelegation.to(TimeInterceptor.class))
                )
                .installOn(inst);
    }

}
