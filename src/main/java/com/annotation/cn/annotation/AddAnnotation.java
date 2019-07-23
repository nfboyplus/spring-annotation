package com.annotation.cn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解 @AddAnnotation
 */
@Target(value = {ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AddAnnotation {

    int userId() default 0;

    String userName() default "默认名称";

    String[]arrays();


    /**
     * @interface 用来定义注解
     *
     * @Target 说明了Annotation所修饰的对象范围
     * 1.	CONSTRUCTOR:用于描述构造器
     * 2.	FIELD:用于描述域
     * 3.	LOCAL_VARIABLE:用于描述局部变量
     * 4.	METHOD:用于描述方法
     * 5.	PACKAGE:用于描述包
     * 6.	PARAMETER:用于描述参数
     * 7.	TYPE:用于描述类、接口(包括注解类型) 或enum声明
     *
     * @Retention 被描述的注解在什么范围内有效
     *
     * @Documented
     *
     * @Inherited
     *
     */

}
