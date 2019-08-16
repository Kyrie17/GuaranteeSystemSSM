package com.ssm.code.service.Impl;

import com.ssm.code.dao.RepairManMapper;
import com.ssm.code.pojo.RepairMan;
import com.ssm.code.service.RepairManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairManServiceImpl implements RepairManService {

    @Autowired
    RepairManMapper repairManMapper;

    @Override
    public List<RepairMan> getRepairManBySerType(int serType) {
        return repairManMapper.getRepairManBySerType(serType);
    }

    @Override
    public List<RepairMan> getAllRepairMan() {
        return repairManMapper.getAllRepairMan();
    }
}
