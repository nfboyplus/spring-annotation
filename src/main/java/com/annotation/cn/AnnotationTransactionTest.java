package com.annotation.cn;

import com.annotation.cn.service.UserService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 自定义注解：手写事务
 */
public class AnnotationTransactionTest {

    /**
     * 1.定义注解
     * 2.封装手动事务
     * 3.具体如何扫包
     */

    public static void main(String[] args){
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        UserService userService = (UserService) applicationContext.getBean("userServiceImpl");
//        userService.insert();
        userService.add();
    }
}
