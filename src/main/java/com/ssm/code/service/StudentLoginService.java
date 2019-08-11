package com.ssm.code.service;

import com.ssm.code.pojo.LoginState;

import java.util.Map;

public interface StudentLoginService {
    /**
     * 用于学生用户的登录
     * @param stuId
     * @param password
     * @return
     */
    int login(String stuId,String password);

    /**
     * 用于判断学生登录状态
     * @param token
     * @return
     */
    Map<String, LoginState> judegLoginState(String token);
}
