package com.ssm.code.controller;

import com.ssm.code.pojo.AnswerRepairFrom;
import com.ssm.code.pojo.RepairForm;
import com.ssm.code.service.PersonCenterService;
import com.ssm.code.tools.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/personCenter")
public class PersonCenterController {

    @Autowired
    PersonCenterService personCenterService;

    @RequestMapping("/getPersonalRF")
    @ResponseBody
    public AnswerRepairFrom getPersonalRF(@RequestParam("token") String token, @RequestParam("pageNum") String pageNum, @RequestParam("judgeState") int judgeState, @RequestParam("userConfirm") int userConfirm) throws Exception {
        String username= CommonUtils.parseJWT(token).getSubject();
        AnswerRepairFrom answerRepairFrom = personCenterService.getPersonalRF(username, pageNum, judgeState, userConfirm);
        return answerRepairFrom;
    }

    @RequestMapping(value = "/getRFByOrderNum")
    @ResponseBody
    public RepairForm getRFByOrderNum(@RequestParam("orderNumber") String orderNumber){
        return personCenterService.getRFByOrderNum(orderNumber);
    }

    @RequestMapping(value = "/ChangeUserConfirm")
    @ResponseBody
    public Map<String, String> ChangeUserConfirm(@RequestParam("orderNumber") int orderNumber){
        Map<String, String> map = new HashMap<>();
        int m = personCenterService.ChangeUserConfirm(orderNumber);
        if (m == 0){
            map.put("code", "-1");
            map.put("msg", "操作失败");
        } else{
            map.put("code", "1");
            map.put("msg", "操作成功");
        }
        return map;
    }

}
