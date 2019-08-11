package com.ssm.code.controller;

import com.ssm.code.dao.PersonInformMapper;
import com.ssm.code.pojo.Area;
import com.ssm.code.pojo.Build;
import com.ssm.code.pojo.Password;
import com.ssm.code.pojo.PersonInform;
import com.ssm.code.service.PersonInformService;
import com.ssm.code.tools.CommonUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * PersonInformController class
 *
 * @author Flc
 * @date 2019/5/27
 */
@RequestMapping("/personInform")
@Controller
public class PersonInformController {

    @Autowired
    PersonInformService personInformService;

    /**
     * 用于获取所有区域信息
     */
    @RequestMapping("/getAllArea")
    @ResponseBody
    public List<Area> getAllArea(){
        List<Area> allArea=personInformService.getAllArea();
        return allArea;
    }

    /**
     * 获取对应的栋数
     */
    @RequestMapping("/getBuild/{code}")
    @ResponseBody
    public List<Build> getBuild(@PathVariable("code")int code){
        List<Build> build=personInformService.getBuildByArea(code);
        return build;
    }

    /**
     * 补全个人信息
     * @param personInform
     */
    @RequestMapping("/improveInform")
    @ResponseBody
    public int improveInform(@RequestBody PersonInform personInform){
        boolean stuId=personInform.getStuId()==null;
        boolean area=(personInform.getArea()!="1"&&personInform.getArea()!="0");
        boolean build=personInform.getBuild()==null;
        boolean sex=(personInform.getSex()!=1&&personInform.getSex()!=0);
        boolean room=personInform.getRoom()==null;
        if(stuId||area||build||sex||room){
            return 1;
        }
        int answer=personInformService.improveInform(personInform);
        if(answer!=0){
            return 1;
        }else{
            return -1;
        }
    }


    /**
     * 用于修改密码
     * @param password
     * @return
     */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public int modifyPassword(@RequestBody Password password){
        /**
         * 判断是否非法输入
         */
        boolean stuId=password.getStuId()==null||password.getStuId().trim().length()==0;
        boolean oldPassword=password.getOldPassword()==null||password.getOldPassword().trim().length()==0;
        boolean newPassword=password.getNewPassword()==null||password.getNewPassword().trim().length()==0;
        if(stuId||oldPassword||newPassword){
            return -1;
        }
        String temp;
        try {
            temp = CommonUtils.parseJWT(password.getStuId()).getSubject();
            password.setStuId(temp);
        } catch (Exception e) {
            return -1;
        }
        //修改密码
        int answer=personInformService.modifyPassword(password);
        if(answer==1){
            return 1;
        }else{
            return -1;
        }
    }
}
