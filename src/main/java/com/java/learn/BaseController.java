package com.java.learn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BaseController {
    @Autowired(required = false)
    List<Test> tests ;
    @GetMapping("/ping")
    public String ping() {
        tests.forEach(Test::execute);
        return "pong";
    }
}
