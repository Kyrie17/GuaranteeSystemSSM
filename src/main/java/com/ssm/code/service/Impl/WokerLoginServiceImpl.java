package com.ssm.code.service.Impl;

import com.ssm.code.dao.WorkerLoginMapper;
import com.ssm.code.service.WorkerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * WokerLoginServiceImpl class
 *
 * @author Flc
 * @date 2019/5/27
 */
@Service
public class WokerLoginServiceImpl implements WorkerLoginService {

    @Autowired
    WorkerLoginMapper workerLoginMapper;

    @Override
    public int workerLogin(String stuId,String password){
        int num=workerLoginMapper.workerLogin(stuId,password);
        if(num>0){
            return 1;
        }else{
            return -1;
        }
    }
}
