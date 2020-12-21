package com.agent.core;

import com.agent.JavaAgent;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.utility.JavaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentListener implements AgentBuilder.Listener {
    private static Logger logger = LoggerFactory.getLogger(JavaAgent.class);

    @Override
    public void onDiscovery(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
        logger.info("onDiscovery--{},{},{},{}",s,classLoader.getClass(),javaModule.getActualName(),b);
    }

    @Override
    public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b, DynamicType dynamicType) {
        logger.info("onTransformation--{},{},{},{}",typeDescription.getClass(),classLoader.getClass(),javaModule.getActualName(),b);

    }

    @Override
    public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, boolean b) {
        logger.info("onIgnored--{},{},{},{}",typeDescription.getClass(),classLoader.getClass(),javaModule.getActualName(),b);

    }

    @Override
    public void onError(String s, ClassLoader classLoader, JavaModule javaModule, boolean b, Throwable throwable) {
        logger.info("onError--{},{},{},{},{}",s.getClass(),classLoader.getClass(),javaModule.getActualName(),b,throwable.getStackTrace());

    }

    @Override
    public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule, boolean b) {
        logger.info("onError--{},{},{},{}",s.getClass(),classLoader.getClass(),javaModule.getActualName(),b);

    }
}
