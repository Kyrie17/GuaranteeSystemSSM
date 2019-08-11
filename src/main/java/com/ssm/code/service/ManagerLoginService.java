package com.ssm.code.service;

public interface ManagerLoginService {

    /**
     * 管理端的登录
     * @param stuId
     * @param password
     * @return
     */
    int managerLogin(String stuId, String password);
}
