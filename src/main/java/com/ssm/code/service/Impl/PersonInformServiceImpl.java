package com.ssm.code.service.Impl;

import com.ssm.code.dao.PersonInformMapper;
import com.ssm.code.pojo.Area;
import com.ssm.code.pojo.Build;
import com.ssm.code.pojo.Password;
import com.ssm.code.pojo.PersonInform;
import com.ssm.code.service.PersonInformService;
import com.ssm.code.tools.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * PersonInformServiceImpl class
 *
 * @author Flc
 * @date 2019/5/27
 */
@Service
public class PersonInformServiceImpl implements PersonInformService {

    @Autowired
    PersonInformMapper personInformMapper;

    /**
     * 用于获得所有区域
     * @return
     */
    @Override
    public List<Area> getAllArea() {
        List<Area> allArea=personInformMapper.getAllArea();
        return allArea;
    }

    /**
     * 根据区域获取具体栋数
     * @param code
     * @return
     */
    @Override
    public List<Build> getBuildByArea(int code) {
        List<Build> build=personInformMapper.getAllBuildByArea(code);
        return build;
    }

    /**
     * 完善个人信息
     * @param personInform
     * @return
     */
    @Override
    public int improveInform(PersonInform personInform) {
        String judge="1";
        if(judge.equals(personInform.getArea())){
            personInform.setArea("北苑");
        }else{
            personInform.setArea("南苑");
        }
        int answer=personInformMapper.improveInform(personInform);
        return answer;
    }

    /**
     * 修改密码
     * @param password
     * @return
     */
    @Override
    public int modifyPassword(Password password) {
        try {
            List<String> timestamps = personInformMapper.getTime(password.getStuId());
            String timestamp;
            if (timestamps.size() > 0) {
                timestamp = timestamps.get(0);
            } else {
                return -1;
            }
            int num = personInformMapper.judgePassword(Md5Utils.getMd5(password.getOldPassword() + timestamp), password.getStuId());
            if (num > 0) {
                password.setNewPassword(Md5Utils.getMd5(password.getNewPassword() + timestamp));
                int answer = personInformMapper.modifyPassword(password.getStuId(), password.getNewPassword());
                if (answer == 1) {
                    return 1;
                }
            } else {
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }


}
