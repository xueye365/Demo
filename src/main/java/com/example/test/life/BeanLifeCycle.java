package com.example.test.life;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycle {


    public static void main(String[] args) {

        System.out.println("现在开始初始化容器");

        ApplicationContext factory = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println("容器初始化成功");
        // 得到Preson，并使用
        Person person = factory.getBean("person",Person.class);
        System.out.println(person);

        System.out.println("现在开始关闭容器！");
        ((ClassPathXmlApplicationContext)factory).registerShutdownHook();
    }
    
    //  结果
    
    // 这是BeanFactoryPostProcessor实现类构造器！！
    // BeanFactoryPostProcessor调用postProcessBeanFactory方法
    // 这是BeanPostProcessor实现类构造器！！
    // 这是InstantiationAwareBeanPostProcessorAdapter实现类构造器！！
    // InstantiationAwareBeanPostProcessor调用postProcessBeforeInstantiation方法
    // 【构造器】调用Person的构造器实例化
    // InstantiationAwareBeanPostProcessor调用postProcessPropertyValues方法
    // 【注入属性】注入属性address
    // 【注入属性】注入属性name
    // 【注入属性】注入属性phone
    // 【BeanNameAware接口】调用BeanNameAware.setBeanName()
    // 【BeanFactoryAware接口】调用BeanFactoryAware.setBeanFactory()
    // BeanPostProcessor接口方法postProcessBeforeInitialization对属性进行更改！
    // 【InitializingBean接口】调用InitializingBean.afterPropertiesSet()
    // 【init-method】调用<bean>的init-method属性指定的初始化方法
    // BeanPostProcessor接口方法postProcessAfterInitialization对属性进行更改！
    // InstantiationAwareBeanPostProcessor调用postProcessAfterInitialization方法
    // 容器初始化成功
    // Person [address=广州, name=张三, phone=110]
    // 现在开始关闭容器！
    // 【DiposibleBean接口】调用DiposibleBean.destory()
    // 【destroy-method】调用<bean>的destroy-method属性指定的初始化方法


}
