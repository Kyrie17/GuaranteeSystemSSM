package com.ssm.code.controller;

import com.ssm.code.common.SendMess;
import com.ssm.code.service.RegisterService;
import com.ssm.code.tools.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * RegisterController class
 *
 * @author Flc
 * @date 2019/5/19
 */
@Controller
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;


    /**
     * 用于检查学号是否已经被注册
     *
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> judgeStudentId(@PathVariable("id") String studentId) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            //调用service层查询是否已经注册
            int answer = registerService.judgeStudentId(studentId);
            if (answer == 1) {
                map.put("message", "该学号可以被注册");
            } else {
                map.put("message", "该学号已被注册");
            }
        }catch (Exception e){
            map.put("message", "该学号已被注册");
        }finally {
            return map;
        }
    }

    /**
     * 用于发送手机验证码
     *
     * @param phoneNumber
     * @return
     */
    @RequestMapping(value = "/sendMess", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> sendMess(@RequestParam("phone") String phoneNumber) {
        Map<String, String> mess = new HashMap<String, String>();
        try {
            SendMess message = new SendMess();
            //发信验证码
            message.SendMessage(phoneNumber);
            //获取验证码
            mess.put("mess", message.getRod());
        } catch (Exception e) {
            mess.put("mess", "发送失败");
        }finally {
            return mess;
        }
    }

    /**
     * 用于注册学生信息
     *
     * @param stuId
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public Map<String, String> submitRegister(@RequestParam("stuId") String stuId, @RequestParam("phone") String phone, @RequestParam("password") String password) {
        Map<String, String> map = new HashMap<>();
        try {
            stuId = stuId.trim();
            phone = phone.trim();
            password = password.trim();

            //判断是否非法输入
            if (stuId == null || stuId.length() == 0 || phone == null || phone.length() == 0 || password == null || phone.length() == 0) {
                map.put("message", "注册失败");
                return map;
            }

            //对密码进行加密
            String timestamp = Md5Utils.getTimestamp();
            String encoded = Md5Utils.getMd5(password + timestamp);

            //调用service层注册学生信息
            int answer = registerService.insertStudent(stuId, phone, encoded, timestamp);
            if (answer == 1) {
                map.put("message", "注册成功");
            } else {
                map.put("message", "注册失败");
            }
        } catch (Exception e) {
            map.put("message", "注册失败");
        }finally {
            return map;
        }
    }
}
