package com.annotation.cn.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * created on 2019/7/23 15:59
 *
 * @author nfboy_liusong@163.com
 * @version 1.0.0
 */
@Repository
public class LogDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(Integer id, String name) {
        String sql = "INSERT INTO test_log(id, log_name) VALUES(?,?);";
        int updateResult = jdbcTemplate.update(sql, id, name);
        System.out.println("##LogDao##updateResult:" + updateResult);
    }
}
