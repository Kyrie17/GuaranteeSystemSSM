package com.ssm.code.service;

import com.ssm.code.pojo.AnswerRepairFrom;
import com.ssm.code.pojo.RepairForm;
import org.springframework.web.bind.annotation.RequestParam;

public interface PersonCenterService {

    /**
     * 获取个人的保修单，分类别
     * @param username
     * @param pageNum
     * @param judgeState
     * @param userConfirm
     * @return
     */
    public AnswerRepairFrom getPersonalRF(String username, String pageNum, int judgeState, int userConfirm);

    /**
     * 通过orderNumber获取报修单
     * @param orderNumber
     * @return
     */
    public RepairForm getRFByOrderNum(String orderNumber);

    /**
     * 改变报修单的UserConfirm
     * @param orderNumber
     * @return
     */
    public int ChangeUserConfirm(int orderNumber);
}
