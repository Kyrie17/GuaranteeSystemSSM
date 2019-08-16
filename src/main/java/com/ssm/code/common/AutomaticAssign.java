package com.ssm.code.common;

import com.ssm.code.dao.RepairFormMapper;
import com.ssm.code.dao.RepairManMapper;
import com.ssm.code.pojo.RepairMan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AutomaticAssign {
    @Autowired
    RepairFormMapper repairFormMapper;

    @Autowired
    RepairManMapper repairManMapper;

    /**
     * 自动分配维修人员
     */
    public String automaticAssign(int serType) {

        //分配维修工
        List<String> list =  repairFormMapper.getRepairMan(serType);	//维修单中出现同报修类型的维修工
        List<RepairMan> list2 = repairManMapper.getRepairMan(serType);		//维修团队出现同报修类型的维修工
        String repairMan = "";

        if(list.size() < list2.size()) {	//如果报修单中没有报修类型的记录
            int index = 0;	//记录报修单中没有的维修工
            for(int i = 0; i < list2.size(); i++) {
                boolean flag = false;
                for(int j = 0; j < list.size(); j++) {
                    if(list2.get(i).getUsername().equals(list.get(j))) {
                        flag = true;
                    }
                }
                if(flag == false) {
                    repairMan = list2.get(i).getUsername();
                }
            }
        }else {

            int[] array = new int[10];
            for(int i = 0; i < list.size(); i++) {
                String repairman = list.get(i);
                array[i] = repairFormMapper.getRepairManNum(repairman);
            }
            int min = 100000;
            int index = 0;
            for(int i = 0; i < list.size(); i++) {
                if(array[i] < min) {
                    min = array[i];
                    index = i;
                }
            }
            repairMan = list.get(index);
        }

        return repairMan;
    }
}
