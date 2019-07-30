package com.example.demo.entity.requestAndResponse;

import com.example.demo.entity.UserEntity;
import com.example.demo.enums.ResultEnum;

public class TestResponse {

    ResultEnum result;
    UserEntity userEntity;

    public TestResponse(ResultEnum result, UserEntity userEntity) {
        this.result = result;
        this.userEntity = userEntity;
    }

    public ResultEnum getResult() {
        return result;
    }

    public void setResult(ResultEnum result) {
        this.result = result;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
