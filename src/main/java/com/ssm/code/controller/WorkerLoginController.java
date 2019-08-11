package com.ssm.code.controller;

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
@RequestMapping("/workerLogin")
@Controller
public class WorkerLoginController {

    @Autowired
    WorkerLoginService workerLoginService;


    /**
     * 用于工人端的登录
     * @param workId
     * @param password
     * @return
     */
    @RequestMapping("")
    @ResponseBody
    public Map<String,String> workerLogin(@RequestParam("workId")String workId,@RequestParam("password")String password){
        Map<String,String> map=new HashMap<>();
        int answer=workerLoginService.workerLogin(workId,password);
        if(answer==1){
            // 创建用户令牌
            String token = CommonUtils.createJWT(workId, 30 * 60 * 1000);
            map.put("answer",token);
        }else{
            map.put("answer","登录失败");
        }
        return map;
    }

}
