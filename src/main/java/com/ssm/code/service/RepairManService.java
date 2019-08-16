package com.ssm.code.service;

import com.ssm.code.pojo.RepairMan;

import java.util.List;

public interface RepairManService {

    /**
     * 获取serType的维修工
     * @return
     */
    public List<RepairMan> getRepairManBySerType(int serType);

    /**
     * 获取所有维修工
     * @return
     */
    public List<RepairMan> getAllRepairMan();
}
