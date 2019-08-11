package com.ssm.code.service.Impl;

import com.ssm.code.dao.StudentLoginMapper;
import com.ssm.code.pojo.LoginState;
import com.ssm.code.service.StudentLoginService;
import com.ssm.code.tools.CommonUtils;
import com.ssm.code.tools.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * LoginServiceImpl class
 *
 * @author Flc
 * @date 2019/5/24
 */
@Service
public class LoginServiceImpl implements StudentLoginService {

    @Autowired
    StudentLoginMapper studentLoginMapper;


    /**
     * 用于学生登录
     * @param stuId
     * @param password
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,rollbackFor = Exception.class)
    public int login(String stuId, String password) {
        String timeStamp= studentLoginMapper.getTimeStamp(stuId);
        if(timeStamp==null){
            return -2;
        }
        //对密码进行加密
        String encoded = Md5Utils.getMd5(password + timeStamp);
        int n= studentLoginMapper.login(stuId,encoded);
        if(n==1){
            return n;
        }else{
            return -1;
        }
    }

    /**
     * 判断学生的登录状态
     * @param token
     * @return
     */
    @Override
    @Transactional
    public Map<String, LoginState> judegLoginState( String token){
        Map<String,LoginState> map=new HashMap<>();
        String stuId=null;
        LoginState loginState=new LoginState();
        try{
            stuId= CommonUtils.parseJWT(token).getSubject();
            loginState.setLoginState("已登录");
            loginState.setStuId(stuId);
        }catch (Exception e){
            e.printStackTrace();
            loginState.setLoginState("尚未登录");
            loginState.setStuId(null);
        }finally {
            map.put("State",loginState);
            return map;
        }

    }
}
