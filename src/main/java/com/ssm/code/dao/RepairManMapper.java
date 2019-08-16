package com.ssm.code.dao;

import com.ssm.code.pojo.RepairMan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairManMapper {
    /**
     * 获取某一报修类型的维修工人
     * @param major
     * @return
     */
    public List<RepairMan> getRepairMan(int major);

    /**
     * 获取所有的维修工人
     * @return
     */
    public List<RepairMan> getAllRepairMan();

    /**
     *获取serType的维修工
     * @return
     */
    public List<RepairMan> getRepairManBySerType(int serType);

}
