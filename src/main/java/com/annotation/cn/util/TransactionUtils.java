package com.annotation.cn.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * created on 2019/7/22 9:36
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Component
@Scope("prototype") // 每个事务都是新的实例 目的解决线程安全问题 多例子
public class TransactionUtils {

    /**
     * 获取事务源
     */
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * 开启事务
     */
    public TransactionStatus begin(){
        TransactionStatus transaction = dataSourceTransactionManager.getTransaction(new DefaultTransactionAttribute());
        return transaction;
    }

    /**
     * 提交事务
     */
    public void commit(TransactionStatus transaction){
        dataSourceTransactionManager.commit(transaction);
    }

    /**
     * 回滚事务
     */
    public void rollback(TransactionStatus transaction){
        dataSourceTransactionManager.rollback(transaction);
    }

}
