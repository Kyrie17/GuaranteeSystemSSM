package com.ssm.code.controller;

import com.ssm.code.pojo.RepairMan;
import com.ssm.code.service.RepairManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("repairMan")
public class RepairManController {

    @Autowired
    RepairManService repairManService;

    @RequestMapping("getRepairManBySerType")
    @ResponseBody
    public List<RepairMan> getRepairManBySerType(@RequestParam("serType") int serType){
        List<RepairMan> list = repairManService.getRepairManBySerType(serType);
        return list;
    }

    @RequestMapping("getAllRepairMan")
    @ResponseBody
    public List<RepairMan> getAllRepairMan(){
        List<RepairMan> list = repairManService.getAllRepairMan();
        return list;
    }
}
