package com.ssm.code.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterMapper {
    /**
     * 用于检查学号是否已经被注册
     * @return
     */
    Integer judgeStudentId(String studentId);

    /**
     * 用于注册学生
     * @param stuId
     * @param phone
     * @param password
     * @param timestamp
     * @return
     */
     Integer insertStudent(@Param("stuId") String stuId, @Param("phone") String phone, @Param("password")  String password,@Param("timestamp") String timestamp);
}
