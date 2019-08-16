package com.ssm.code.service;

import com.ssm.code.pojo.AnswerRepairFrom;

public interface WorkerService {

    /**
     * 维修工获取要处理的报修单
     */
    public AnswerRepairFrom getRepairManRF(String token, String pageNum, String judgeState, String userConfirm) ;

    /**
     * 维修工改变报修单状态
     */
    public int changeJudgeState(int orderNumber, int judgeState);

}
