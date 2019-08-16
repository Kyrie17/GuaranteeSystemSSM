package com.ssm.code.controller;

import com.ssm.code.pojo.AnswerRepairFrom;
import com.ssm.code.pojo.RepairForm;
import com.ssm.code.service.RepairFormService;
import com.ssm.code.tools.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RepairController class
 *
 * @author Flc
 * @date 2019/8/11
 */
@Controller()
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

    @RequestMapping(value = "/submitRepairForm", method = RequestMethod.POST)
    public String submitRepairForm(RepairForm repairForm, HttpServletRequest request){

        int m = repairFormService.submitRepairForm(repairForm, request);

        if(m != 0) {    //报修单信息提交成功
            return "redirect:/html/person.html";
        } else {
            return "redirect:/html/HomePage.html";
        }
    }


    @RequestMapping(value = "getLastestRF")
    @ResponseBody
    public List<RepairForm> getLastestRF(){
        List<RepairForm> list = repairFormService.getLastestRF();
        return list;
    }


    @RequestMapping(value = "/getAllRF")
    @ResponseBody
    public AnswerRepairFrom getAllRF(@RequestParam("username") String username, @RequestParam("pageNum") String pageNum, @RequestParam("serType") String serType, @RequestParam("judgeState") String judgeState){
        AnswerRepairFrom answerRepairFrom = repairFormService.getAllRF(username, pageNum, serType, judgeState);
        return answerRepairFrom;
    }

    @RequestMapping(value = "/removeRF", method = RequestMethod.POST)
    public String removeRF(@RequestParam("orderNumber") String orderNumber){
        repairFormService.removeRF(orderNumber);
        return "redirect:/html/ManageOperate.html";
    }

    @RequestMapping(value = "/rfUpdate", method = RequestMethod.POST)
    public String rfUpdate(@RequestParam("orderNumber") String orderNumber, @RequestParam("judgeState") String judgeState, @RequestParam("repairMan") String repairMan){
        repairFormService.updateRFInfor(orderNumber, judgeState, repairMan);
        return "redirect:/html/ManageOperate.html";
    }
}
