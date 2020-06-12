package com.example.demo.proxy.service.impl;

import com.example.demo.proxy.service.IUserService;

public class UserServiceImpl implements IUserService {
    @Override
    public void insert(String name) {
        System.out.println(String.format("用户[name:%s]插入成功!", name));
    }
}
