package com.ssm.code.controller;

import com.ssm.code.service.AnalyzeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * AnalyzeDataController class
 *
 * @author Flc
 * @date 2019/5/25
 */
@Controller
@RequestMapping("/analyzeData")
public class AnalyzeDataController {
    @Autowired
    AnalyzeDataService analyzeDataService;

    /**
     * 用于获取首页的数据分析
     * @return
     */
    @RequestMapping("/analyze")
    @ResponseBody
    public int[] getHomeAnalyzeData() {
        int[] answer = analyzeDataService.getHomeAnalyzeData();
        return answer;
    }


    /**
     * 用于获取管理端的数据分析
     * @param num
     * @return
     */
    @RequestMapping(value = "/{num}", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, int[]> getAnalyzeData(@PathVariable("num") int num) {
        Map<String, int[]> map = new HashMap<>();
        if (num == 1) {
            int[] answer = analyzeDataService.getSertype();
            map.put("data", answer);
        } else if (num == 2) {
            map.put("data", analyzeDataService.getJudgestate());
        } else if (num == 3) {
            map.put("data", analyzeDataService.getSertime());
        } else if (num == 4) {
            map.put("data", analyzeDataService.getMajor());
        } else {
            map = null;
        }

        return map;
    }
}
