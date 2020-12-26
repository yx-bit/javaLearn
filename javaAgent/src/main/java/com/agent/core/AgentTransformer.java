package com.agent.core;

import com.agent.JavaAgent;
import com.agent.interceptor.TimeInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentTransformer implements AgentBuilder.Transformer {
    private static Logger logger = LoggerFactory.getLogger(JavaAgent.class);

    @Override
    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {
        logger.info("transform--{},{},{},{}",builder.getClass(),typeDescription.getClass(),classLoader.getClass(),javaModule.getClass());
        return builder
                .method(ElementMatchers.any()) // 拦截任意方法
                .intercept(MethodDelegation.to(TimeInterceptor.class)); // 委托
    }
}
