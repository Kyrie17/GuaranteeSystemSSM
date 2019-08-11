package com.ssm.code.controller;

import com.ssm.code.common.ImageMess;
import com.ssm.code.pojo.LoginState;
import com.ssm.code.service.StudentLoginService;
import com.ssm.code.tools.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * LoginController class
 * @author Flc
 * @date 2019/5/23
 */
@Controller
@RequestMapping("/login")
public class StudentLoginController {

    @Autowired
    StudentLoginService loginService;

    /**
     * 图片验证码
     *
     * @return
     */
    @RequestMapping(value = "/getImageMess", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> sendImage() {
        Map<String, String> map = new HashMap<>();
        try {
            ImageMess imageMess = new ImageMess();
            //发送验证码
            String mess = imageMess.getIamgeMess();
            //获得验证码
            String answer = imageMess.getMess();
            map.put("mess", mess);
            map.put("answer", answer);
        } catch (Exception e) {
            map.put("mess", "-1");
        }finally {
            return map;
        }

    }


    /**
     * 用于学生登录
     *
     * @param stuId
     * @param password
     * @return
     */
    @RequestMapping("")
    @ResponseBody
    public Map<String, String> login(@RequestParam("stuId") String stuId, @RequestParam("password") String password) {
        Map<String, String> map = new HashMap<>();
        if (stuId == null || stuId.trim().length() == 0 || password == null || password.trim().length() == 0) {
            map.put("msg", "登录失败");
            return map;
        }
        stuId = stuId.trim();
        password = password.trim();
        int answer1=1;
        int answer2=-2;
        try {
            int n = loginService.login(stuId, password);
            if (n == answer1) {
                // 创建用户令牌
                String token = CommonUtils.createJWT(stuId, 30 * 60 * 1000);
                map.put("data",token);
                map.put("msg", "登录成功");
            } else if (n == answer2) {
                map.put("msg", "不存在该学号");
            } else {
                map.put("msg", "学号或密码错误");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "登录失败");
        }finally {
            return map;
        }

    }

    /**
     * 判断学生的登录状态
     * @param token
     * @return
     */
    @RequestMapping("/judgeLoginState")
    @ResponseBody
    public Map<String, LoginState> judgeLoginState(@RequestParam("token") String token){
        Map<String,LoginState> map=new HashMap<>();
        if(token==null||token.trim().length()==0){
            LoginState loginState=new LoginState("尚未登录",null);
            map.put("State",loginState);
            return map;
        }
        Map<String,LoginState> answer=loginService.judegLoginState(token);
        return answer;
    }
}
