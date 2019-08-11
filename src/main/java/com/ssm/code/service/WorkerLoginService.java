package com.ssm.code.service;

public interface WorkerLoginService {

    /**
     * 工人端的登录
     * @param stuId
     * @param password
     * @return
     */
    int workerLogin(String stuId,String password);
}
