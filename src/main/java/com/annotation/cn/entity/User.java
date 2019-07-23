package com.annotation.cn.entity;

import com.annotation.cn.annotation.AddAnnotation;

/**
 * created on 2019/7/22 16:17
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
public class User {

    @AddAnnotation(userName = "张三", userId = 18, arrays = { "1" })
    public void add(){
    }
}
