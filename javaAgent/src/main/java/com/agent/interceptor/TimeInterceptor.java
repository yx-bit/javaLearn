package com.agent.interceptor;

import com.agent.JavaAgent;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.implementation.bind.annotation.This;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class TimeInterceptor  {
    private static Logger logger = LoggerFactory.getLogger(JavaAgent.class);
    @RuntimeType
    public static Object intercept(
                                   @Origin Method method,
                                   @SuperCall Callable<?> callable) throws Exception {
        long start = System.currentTimeMillis();
        try {
            // 原有函数执行
            return callable.call();
        } finally {
            logger.info(method.getName() + ": took " + (System.currentTimeMillis() - start) + "ms");
        }
    }
}