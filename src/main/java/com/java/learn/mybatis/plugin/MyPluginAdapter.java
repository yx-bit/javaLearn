package com.java.learn.mybatis.plugin;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

public class MyPluginAdapter extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        int i=1;
        if (i == 1) {
            throw new RuntimeException("ll");
        }
        return false;
    }
}
