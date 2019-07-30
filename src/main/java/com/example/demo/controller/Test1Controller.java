package com.example.demo.controller;

import com.example.demo.service.Test1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test1")
public class Test1Controller {

        @Autowired
        private Test1Service test1Service;

        /**
         * 测试Spring
         * @param name
         */
        @RequestMapping(value = "insert", method = RequestMethod.GET)
        public void testRepaymentAdvise(@RequestParam("name") String name){
                test1Service.insert(name);
        }

        /**
         * 测试aop
         */
        @RequestMapping(value = "testAop", method = RequestMethod.GET)
        public void testAop(@RequestParam("name") String name, @RequestParam("userId") Integer userId){
                test1Service.testAop(name, userId);
        }





}
