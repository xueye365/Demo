package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class Test1Service {

    private AtomicInteger id = new AtomicInteger();

    public UserEntity insert(String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);
        userEntity.setId(id.getAndIncrement());
        return userEntity;
    }

}
