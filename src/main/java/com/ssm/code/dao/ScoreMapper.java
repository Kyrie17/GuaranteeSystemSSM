package com.ssm.code.dao;

import com.ssm.code.pojo.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreMapper {

    /**
     * 插入评价
     * @param score
     * @return
     */
    int insertScore(@Param("Score")Score score);
}
