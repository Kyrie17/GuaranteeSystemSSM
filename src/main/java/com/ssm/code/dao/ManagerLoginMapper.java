package com.ssm.code.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * WorkerLoginMapper class
 *
 * @author Flc
 * @date 2019/5/27
 */
@Repository
public interface ManagerLoginMapper {

    /**
     * 用于管理员的登录
     * @param managerId
     * @param password
     * @return
     */
    int managerLogin(@Param("managerId") String managerId, @Param("password") String password);
}
