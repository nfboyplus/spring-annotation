package com.annotation.cn;

import com.annotation.cn.annotation.AddAnnotation;

import java.lang.reflect.Method;

/**
 * 利用反射机制获取注解信息
 */
public class AddAnnotationTest {

    public static void main(String[] args) throws ClassNotFoundException {
        // 怎么样获取到方法上注解信息 反射机制
        Class<?> forName = Class.forName("com.annotation.cn.entity.User");
        // 获取到当前类（不包含继承）所有的方法
        Method[] declaredMethods = forName.getDeclaredMethods();
        for (Method method : declaredMethods) {
            // 获取该方法上是否存在注解
            System.out.println("---- 方法名称：" + method.getName() + " ----");
            AddAnnotation addAnnotation = method.getDeclaredAnnotation(AddAnnotation.class);
            if (addAnnotation == null) {
                // 该方法上没有注解
                System.out.println("---- 该方法上没有加注解 ----");
                continue;
            }
            // 在该方法上查找到该注解
            System.out.println("---- userId：" + addAnnotation.userId() + " ----");
            System.out.println("---- userName：" + addAnnotation.userName() + " ----");
            System.out.println("---- arrays：" + addAnnotation.arrays()+ " ----");
        }
    }
}
