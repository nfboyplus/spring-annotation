package com.annotation.cn.service.impl;

import com.annotation.cn.annotation.ExtTransaction;
import com.annotation.cn.dao.UserDao;
import com.annotation.cn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * created on 2019/7/22 9:34
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @ExtTransaction
    public void insert() {
        userDao.insert(1, "刘松", 28);
        System.out.println("---- 向数据库添加数据 ----");
        // 加入事务：抛出异常，不继续执行，上面的数据未插入到数据库，数据回滚；
        int i = 10/0;
        userDao.insert(2, "刘刘",18);
    }

    /**
     * 注意：开启事务的时候，不要 try catch，将异常抛给外层AOP处理；
     */

}
