package com.ssm.code.controller;

import com.ssm.code.service.RepairFormService;
import com.ssm.code.tools.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * RepairController class
 *
 * @author Flc
 * @date 2019/8/11
 */
@Controller
@RequestMapping("/repair")
public class RepairFormController {

    @Autowired
    RepairFormService repairFormService;

    @RequestMapping(value = "/judgeFinshInform")
    @ResponseBody
    public Map<String,String> judgeFinshInform(@RequestParam("token") String token) {
        String stuId = "";
        Map<String,String> map = new HashMap<>();
        try {
            stuId = CommonUtils.parseJWT(token).getSubject();
            int answer = repairFormService.judgeFinshInform(stuId);
            if(answer == 1) {
                map.put("msg","已完善个人信息");
            }else {
                map.put("msg","尚未完善个人信息");
            }
        } catch (Exception e) {
            map.put("msg","尚未完善个人信息");
        }finally {
            return map;
        }
    }
}
