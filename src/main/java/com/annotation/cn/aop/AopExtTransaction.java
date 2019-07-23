package com.annotation.cn.aop;

import com.annotation.cn.annotation.ExtTransaction;
import com.annotation.cn.util.TransactionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.lang.reflect.Method;

/**
 * 自定义注解的具体实现
 */
@Aspect    //切面
@Component //注入到容器中
public class AopExtTransaction {

    /**
     * TransactionUtils 不要实现为单例子： 如果为单例子的话可能会发生线程安全问题
     * 设置原型模式：@Scope("prototype")
     */
    @Autowired
    private TransactionUtils transactionUtils;

    /**
     * 异常处理：回滚
     */
    @AfterThrowing("execution(* com.annotation.cn.service.*.*(..)))")
    public void afterThrowing() {
        //第一种：获取到当前事务进行回滚
//         TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        //第二种：调用封装的方法
        transactionUtils.rollback();
    }

    /**
     * 环绕通知 在方法之前和之后处理事情
     */
    @Around("execution(* com.annotation.cn.service.*.*(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        //获取该方法上是否加注解
        ExtTransaction extTransaction = getExtTransaction(proceedingJoinPoint);
        //调用方法执行之前
        TransactionStatus transactionStatus = begin(extTransaction);
        //代理调用方法 注意点： 如果调用方法抛出异常不会执行后面代码
        proceedingJoinPoint.proceed();
        //调用方法之后执行
        commit(transactionStatus);
    }

    /**
     * 开启事务
     */
    private TransactionStatus begin(ExtTransaction extTransaction) {
        //判断是否有自定义事务注解
        if (null == extTransaction) {
            return null;
        }
        //如果有自定义注解，开启事务
        return transactionUtils.begin();
    }

    /**
     * 提交事务
     */
    private void commit(TransactionStatus transactionStatus) {
        //如果事务不为空，则提交事务
        if (null != transactionStatus) {
            transactionUtils.commit(transactionStatus);
        }

    }

    /**
     * 获取注解
     */
    private ExtTransaction getExtTransaction(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException {
        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        //获取目标对象
        Class<?> classTarget = proceedingJoinPoint.getTarget().getClass();
        //获取目标对象类型
        Class<?>[] par = ((MethodSignature) proceedingJoinPoint.getSignature()).getParameterTypes();
        //获取目标对象方法
        Method objMethod = classTarget.getMethod(methodName, par);
        ExtTransaction extTransaction = objMethod.getDeclaredAnnotation(ExtTransaction.class);
        if (null == extTransaction) {
            System.out.println("---- 您的方法上,没有加入注解 ----");
            return null;
        }
        return extTransaction;
    }


    /**
     * 未重构的代码：
     *
     *    1.获取代理对象的方法
     *    //获取方法名称
     *    String methodName = proceedingJoinPoint.getSignature().getName();
     *    //获取目标对象
     *    Class<?> classTarget = proceedingJoinPoint.getTarget().getClass();
     *    //获取目标对象类型
     *    Class<?>[] par = ((MethodSignature)proceedingJoinPoint.getSignature()).getParameterTypes();
     *    //获取目标对象方法
     *    Method obMethod = classTarget.getMethod(methodName, par);
     *    ExtTransaction extTransaction = obMethod.getDeclaredAnnotation(ExtTransaction.class);
     *    //2.获取该方法上是否加上注解
     *    TransactionStatus transactionStatus = null;
     *    if (null != extTransaction){
     *        //3.如果存在事务，开启事务
     *        transactionStatus = transactionUtils.begin();
     *    }
     *    //4.调用目标代理对象方法
     *    proceedingJoinPoint.proceed();
     *    //5.判断该方法上是否加上注解
     *    if (null != extTransaction){
     *        //6.如果存在注解，提交事务
     *        transactionUtils.commit(transactionStatus);
     *   }
     */
}
