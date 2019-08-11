package com.ssm.code.controller;

import com.ssm.code.service.ManagerLoginService;
import com.ssm.code.service.WorkerLoginService;
import com.ssm.code.tools.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * WorkerLoginController class
 *
 * @author Flc
 * @date 2019/5/27
 */
@RequestMapping("/managerLogin")
@Controller
public class ManagerLoginController {

    @Autowired
    ManagerLoginService managerLoginService;


    /**
     * 用于管理端的登录
     * @param managerId
     * @param password
     * @return
     */
    @RequestMapping("")
    @ResponseBody
    public Map<String,String> managerLogin(@RequestParam("managerId")String managerId,@RequestParam("password")String password){
        Map<String,String> map=new HashMap<>();
        int answer=managerLoginService.managerLogin(managerId,password);
        if(answer==1){
            // 创建用户令牌
            String token = CommonUtils.createJWT(managerId, 30 * 60 * 1000);
            map.put("answer",token);
        }else{
            map.put("answer","登录失败");
        }
        return map;
    }

}
