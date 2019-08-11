package com.ssm.code.service;

import com.ssm.code.pojo.Area;
import com.ssm.code.pojo.Build;
import com.ssm.code.pojo.Password;
import com.ssm.code.pojo.PersonInform;

import java.util.List;

public interface PersonInformService {
    /**
     * 获取所有区域
     * @return
     */
    List<Area> getAllArea();

    /**
     * 通过区域获取对应的栋数
     * @param code
     * @return
     */
    List<Build> getBuildByArea(int code);

    /**
     *用于完善个人信息
     * @param personInform
     * @return
     */
    int improveInform(PersonInform personInform);

    /**
     * 用于修改密码
     * @param password
     * @return
     */
    int modifyPassword(Password password);
}
