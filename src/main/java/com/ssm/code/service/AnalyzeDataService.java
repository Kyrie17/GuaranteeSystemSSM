package com.ssm.code.service;


public interface AnalyzeDataService {

    /**
     * 用于获取主页的数据分析
     * @return
     */
    int[] getHomeAnalyzeData();

    /**
     * 用于获取维修类型
     * @return
     */
    int[] getSertype();

    /**
     * 用于获取维修状态
     * @return
     */
    int[] getJudgestate();

    /**
     * 用于获取报修时间
     * @return
     */
    int[] getSertime();

    /**
     * 用于获取维修工类型
     * @return
     */
    int[] getMajor();
}
