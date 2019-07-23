package com.annotation.cn.service.impl;

import com.annotation.cn.dao.LogDao;
import com.annotation.cn.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * created on 2019/7/23 16:03
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    /**
     * 日志正常插入数据库
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addLog() {
        logDao.add(1,"日志");
    }

    /**
     * 事物传播行为：@Transactional(propagation = Propagation.NEVER)
     * PROPAGATION_REQUIRED —如果当前有事务，就用当前事务，如果当前没有事务，就新建一个事务。这是最常见的选择。
     * PROPAGATION_SUPPORTS --支持当前事务，如果当前没有事务，就以非事务方式执行。//如果外层方法没有事务，就会以非事务进行执行。
     * PROPAGATION_MANDATORY --支持当前事务，如果当前没有事务，就抛出异常。
     * PROPAGATION_REQUIRES_NEW --新建事务，如果当前存在事务，把当前事务挂起。
     * PROPAGATION_NOT_SUPPORTED- -以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。
     * PROPAGATION_NEVER --以非事务方式执行，如果当前存在事务，则抛出异常。
     * 默认传播行为为REQUIRED
     */

}
