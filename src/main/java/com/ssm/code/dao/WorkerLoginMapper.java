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
public interface WorkerLoginMapper {

    /**
     * 用于工人的登录
     * @param workerId
     * @param password
     * @return
     */
    int workerLogin(@Param("workerId")String workerId,@Param("password")String password);
}
