package com.ssm.code.service;

import com.ssm.code.pojo.Score;

public interface ScoreService {

    /**
     * 插入评价
     * @param score
     * @return
     */
    int insertScore(Score score);
}
