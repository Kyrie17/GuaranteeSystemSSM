package com.ssm.code.dao;

import com.ssm.code.pojo.RepairForm;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerMapper {

    /**
     * 维修工获取要处理的报修单
     */
    public List<RepairForm> getRFByRepairMan(String repairMan);

    /**
     * 维修工改变报修单状态
     */
    public int changeJudgeState( @Param("judgeState") int judgeState, @Param("orderNumber") int orderNumber);

}
