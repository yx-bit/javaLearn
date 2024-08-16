package com.java.learn.mockito;

import org.springframework.stereotype.Service;

@Service
public class InnerService {

    public String testEx(String s) {
        if (1 == 1) {
            throw new RuntimeException("Hello teacher");
        }
        return "Hello world";
    }

}
