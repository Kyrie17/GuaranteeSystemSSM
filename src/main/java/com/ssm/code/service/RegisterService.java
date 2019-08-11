package com.ssm.code.service;

public interface RegisterService {
    /**
     * 用于检查学号是否已经被注册
     * @param studentId
     * @return
     */
    public int judgeStudentId(String studentId);

    /**
     * 用于注册学生信息
     * @param stuId
     * @param phone
     * @param password
     * @param timestamp
     * @return
     */
    int insertStudent(String stuId,String phone,String password,String timestamp);
}
