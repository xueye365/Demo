package com.example.demo.aspect;

import com.example.demo.annotation.TestAnnotation;
import com.example.demo.entity.requestAndResponse.TestResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component("testAnnotationAspect")
public class TestAnnotationAspect {

    /**
     * 如果一个方法，不仅作为切点添加了切面，而且还添加了事务，那么当这个方法抛出异常时，总是切面先捕获到异常，并且吞掉了这个异常。而事务是捕获不到这个异常的，因此事务是不生效的
     */


    @Pointcut("execution(* com.example.demo.service.Test1Service.testAop(..))")
    public void pointcut() {
    }

    @AfterReturning(value = "pointcut()", returning = "response")
    public void afterReturning(JoinPoint joinPoint, TestResponse response) throws Exception {
        if (response == null) {
            return;
        }
        System.out.println("AfterReturning");
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        // 获取标签参数
        TestAnnotation annotation = method.getAnnotation(TestAnnotation.class);
        // 获取参数
        Integer loanId = Integer.parseInt(String.valueOf(joinPoint.getArgs()[1]));
        System.out.println("AfterReturning" + response.getResult() + response.getUserEntity().getName());
        System.out.println("loanid" + loanId);
        throw new Exception("在切面抛出异常");
    }

    @After("pointcut()")
    public void after(JoinPoint joinPoint) {
        System.out.println("after");
    }


    @AfterThrowing(value = "pointcut()", throwing = "e")
    public void AfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("AfterThrowing" + e.getMessage());
    }

//
//    try{
//        try{
//            //@Before
//            method.invoke(..);
//        }finally{
//            //@After
//        }
//        //@AfterReturning
//    }catch(){
//        //@AfterThrowing
//    }

}
