package com.ssm.code.controller;

import com.ssm.code.pojo.Score;
import com.ssm.code.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ScoreController class
 *
 * @author Flc
 * @date 2019/5/28
 */
@RequestMapping("/score")
@Controller
public class ScoreController {

    @Autowired
    ScoreService scoreService;


    /**
     * 用于报修单的评价
     * @param studentScore
     * @return
     */
    @RequestMapping("/insertScore")
    @ResponseBody
    public int insertScore(@RequestBody Score studentScore){
        /**
         * 查看各项数据是否非法
         */
        boolean satisfied=studentScore.getSatisfied()==null||studentScore.getSatisfied().trim().length()==0;
        boolean suggest=studentScore.getSuggest()==null||studentScore.getSuggest().trim().length()==0;
        boolean score=studentScore.getScore()<=0||studentScore.getScore()>5;
        boolean flag=!studentScore.getFlag().equals("1");
        boolean username=studentScore.getUsername()==null||studentScore.getUsername().trim().length()==0;
        boolean repairman=studentScore.getRepairman()==null||studentScore.getRepairman().trim().length()==0;
        boolean time=!studentScore.getTime().equals("");
        boolean rOrdernumber=studentScore.getrOrdernumber()<=0;
        if(satisfied||suggest||score||flag||username||repairman||time||rOrdernumber){
            return -1;
        }

        /**
         * 插入评价
         */
        int answer=scoreService.insertScore(studentScore);
        return answer;
    }

}
