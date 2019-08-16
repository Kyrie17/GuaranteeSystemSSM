package com.ssm.code.controller;

import com.ssm.code.pojo.AnswerRepairFrom;
import com.ssm.code.pojo.AnswerScore;
import com.ssm.code.service.ScoreService;
import com.ssm.code.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;

    @Autowired
    ScoreService scoreService;

    @RequestMapping("/getRepairManRF")
    @ResponseBody
    public AnswerRepairFrom getRepairManRF(@RequestParam("token") String token, @RequestParam("pageNum") String pageNum, @RequestParam("judgeState") String judgeState, @RequestParam("userConfirm") String userConfirm){

        return workerService.getRepairManRF(token, pageNum, judgeState, userConfirm);
    }

    @RequestMapping("/changeJudgeState")
    @ResponseBody
    public Map<String, String> changeJudgeState(@RequestParam("orderNumber") int orderNumber, @RequestParam("judgeState") int judgeState){
        Map<String, String> map = new HashMap<>();
        int m = workerService.changeJudgeState(orderNumber, judgeState);
        if (m == 0){
            map.put("code", "-1");
            map.put("msg", "操作失败");
        } else {
            map.put("code", "1");
            map.put("msg", "操作成功");
        }
        return map;
    }

    @RequestMapping("/getScoreByRM")
    @ResponseBody
    public AnswerScore getScoreByRM(@RequestParam("token") String token, @RequestParam("pageNum") String pageNum){
        return scoreService.getScoreByRM(token, pageNum);
    }

    @RequestMapping("/getAvgByRM")
    @ResponseBody
    public Map<String, Double> getAvgByRM(@RequestParam("token") String token){
        Map<String, Double> map = new HashMap<>();
        map.put("code", scoreService.getAvgByRM(token));
        return map;
    }
}
