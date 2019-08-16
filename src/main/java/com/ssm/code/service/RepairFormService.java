package com.ssm.code.service;

import com.ssm.code.pojo.AnswerRepairFrom;
import com.ssm.code.pojo.RepairForm;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * RepairFormService class
 *
 * @author Flc
 * @date 2019/8/11
 */
@Service
public interface RepairFormService {
    /**
     * 判断是否完成个人信息的填写
     * @param stuId
     * @return
     */
    public int judgeFinshInform(String stuId);

    /**
     * 提交报修单
     * @param repairForm
     * @param request
     * @return
     */
    public int submitRepairForm(RepairForm repairForm, HttpServletRequest request);

    /**
     * 获取最近几条报修信息
     */
    public List<RepairForm> getLastestRF();

    /**
     * 按需获取所有报修信息
     */
    public AnswerRepairFrom getAllRF(String username, String pageNum, String serType, String judgeState);

    /**
     * 删除报修单号为orderNumber的报修单
     */
    public int removeRF(String orderNumber);

    /**
     * 修改保修单信息
     */
    public int updateRFInfor(String orderNumber, String judgeState, String repairMan);
}
