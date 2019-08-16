package com.ssm.code.dao;

import com.ssm.code.pojo.RepairForm;
import com.ssm.code.pojo.RepairMan;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairFormMapper {
    /**
     * 判断是否完善个人信息
     * @param stuId
     * @return
     */
    int judgeFinshInform(String stuId);

    /**
     * 查询报修类型是？的所有维修工的名字
     */
    public List<String> getRepairMan(int serType);

    /**
     * 查询维修工是？的报修表单数量
     */
    public int getRepairManNum(String repairMan);

    /**
     * 提交报修单
     * @param rf 报修单
     * @return 操作几行数据
     */
    public int submitRepairForm(RepairForm rf);

    /**
     * 获取最近几条报修信息
     */
    public List<RepairForm> getLastestRF(int nums);

    /**
     * 查询审核状态为未审核和已审核的报修单
     */
    public List<RepairForm> getUnfinishedRF(String username);

    /**
     * 查询审核状态为已完成的报修单
     */
    public List<RepairForm> getFinishedRF(@Param("username") String username, @Param("userConfirm") int userConfirm);

    /**
     * 将报修单用户端更改为确认完成
     */
    public int ChangeUserConfirm(int orderNumber);

    /**
     * 通过保修单编号查询保修单
     */
    public RepairForm getRFByOrderNum(String orderNumber);

    /**
     * 按需查询所有表单
     */
    public List<RepairForm> getAllRF(@Param("username") String username, @Param("serType") int serType, @Param("judgeState") int judgeState);

    /**
     * 删除报修单号为orderNumber的报修单
     */
    public int removeRF(String orderNumber);

    /**
     * 修改保修单信息
     */
    public int updateRFInfor(@Param("orderNumber") String orderNumber, @Param("judgeState") String judgeState, @Param("repairMan") String repairMan);
}
