package com.ssm.code.service;

import com.ssm.code.pojo.AnswerScore;
import com.ssm.code.pojo.Score;

import java.util.List;

public interface ScoreService {

    /**
     * 插入评价
     * @param score
     * @return
     */
    int insertScore(Score score);


    /**
     * 查询对于维修工的评价
     */
    public AnswerScore getScoreByRM(String token, String pageNum);

    /**
     * 对该维修工的评价的平均分
     */
    public double getAvgByRM(String token);
}
