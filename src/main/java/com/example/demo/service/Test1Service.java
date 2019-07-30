package com.example.demo.service;

import com.example.demo.annotation.TestAnnotation;
import com.example.demo.entity.UserEntity;
import com.example.demo.entity.requestAndResponse.TestResponse;
import com.example.demo.enums.ResultEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.Executors;
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


    /**
     * After            没有返回之前执行切面
     * AfterReturning   先执行after，返回数据之后执行AfterReturning切面
     * AfterThrowing    先执行after，再执行AfterThrowing，最后抛出异常
     * @param name
     * @param userId
     * @return
     */
    @TestAnnotation
    public TestResponse testAop(String name, Integer userId) {
        UserEntity userEntity = new UserEntity();
        try {
            try {
                userEntity.setName(name);
                userEntity.setId(userId);
                throw new Exception("12345");
            } catch (Exception e) {
                System.out.println("里面的异常");
                userEntity.name += "里面的异常";
                return new TestResponse(ResultEnum.FAIL, userEntity);
            } finally {
                System.out.println("里面的finally");
                userEntity.name += "里面的finally";
                return new TestResponse(ResultEnum.FAIL, userEntity);
            }
        } catch (Exception e) {
            System.out.println("外面的异常");
            userEntity.name += "外面的异常";
            return new TestResponse(ResultEnum.FAIL, userEntity);
        } finally {
            System.out.println("外面的finally");
            userEntity.name += "外面的finally";
            return new TestResponse(ResultEnum.FAIL, userEntity);
        }
    }


    /**
     * 测试事务和aop是否可以同时使用，结论：可以同时使用，先进入切面，再回滚，使用AfterThrowing测试
     * 测试在切面中抛异常，会不会回滚，使用After测试，结论：会回滚
     * @param name
     * @param userId
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    @TestAnnotation()
    public TestResponse testTransactionAndAop(String name, Integer userId) throws Exception {
//        FlowLog log = new FlowLog();
//        log.setSourceId(4);
//        flowLogMapper.insert(log);
//        throw new Exception("12345");
        return null;
    }




}
