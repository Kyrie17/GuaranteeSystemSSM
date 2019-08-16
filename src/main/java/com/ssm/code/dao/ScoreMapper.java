package com.ssm.code.dao;

import com.ssm.code.pojo.Score;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreMapper {

    /**
     * 插入评价
     * @param score
     * @return
     */
    int insertScore(@Param("Score")Score score);

    /**
     * 查询对于维修工的评价
     */
    public List<Score> getScoreByRM(String repairMan);

    /**
     *
     */
    /**
     * 对该维修工的评价的平均分
     */
    public double getAvgByRM(String repairMan);
}
