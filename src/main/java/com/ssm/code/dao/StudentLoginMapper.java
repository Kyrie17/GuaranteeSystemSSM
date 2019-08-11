package com.ssm.code.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * LoginMapper class
 *
 * @author Flc
 * @date 2019/5/24
 */
@Repository
public interface StudentLoginMapper {
    /**
     * 用于学生进行登录
     * @param stuId
     * @param password
     * @return
     */
    int login(@Param("stuId") String stuId,@Param("password") String password);

    /**
     * 根据学号获取时间戳
     * @param stuId
     * @return
     */
    String getTimeStamp(@Param("stuId")String stuId);
}
