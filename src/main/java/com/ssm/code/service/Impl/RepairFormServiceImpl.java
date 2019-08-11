package com.ssm.code.service.Impl;

import com.ssm.code.dao.RepairFormMapper;
import com.ssm.code.service.RepairFormService;
import com.ssm.code.service.RepairFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RepairServiceImpl class
 *
 * @author Flc
 * @date 2019/8/11
 */
@Service
public class RepairFormServiceImpl implements RepairFormService {

    @Autowired
    RepairFormMapper repairFormMapper;

    @Override
    public int judgeFinshInform(String stuId) {
        int answer = repairFormMapper.judgeFinshInform(stuId);
        if(answer > 0) {
            return -1;
        } else {
            return 1;
        }
    }
}
