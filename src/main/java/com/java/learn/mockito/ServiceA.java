package com.java.learn.mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServiceA {
    @Resource
    private InnerService innerService;
    public String testMocK() {
        String s = innerService.testEx("s");
        return s;
    }
}
