package com.ssm.code.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalyzeDataMapper {

    /**
     * 查找报修类型
     * @param num
     * @return
     */
    int getSertype(@Param("num") int num);

    /**
     * 查找报修状态
     * @param num
     * @return
     */
    int getJudgestate(@Param("num") int num);

    /**
     * 查找报修时间
     * @param month
     * @return
     */
    int getSertime(@Param("month") String month);

    /**
     * 查找维修工类别
     * @param num
     * @return
     */
    int getMajor(@Param("num") int num);
}
