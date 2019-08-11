package com.ssm.code.service.Impl;

import com.ssm.code.dao.ManagerLoginMapper;
import com.ssm.code.service.ManagerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * WokerLoginServiceImpl class
 *
 * @author Flc
 * @date 2019/5/27
 */
@Service
public class ManagerLoginServiceImpl implements ManagerLoginService {

    @Autowired
    ManagerLoginMapper managerLoginMapper;

    @Override
    public int managerLogin(String managerId,String password){
        int num=managerLoginMapper.managerLogin(managerId,password);
        if(num>0){
            return 1;
        }else{
            return -1;
        }
    }
}
