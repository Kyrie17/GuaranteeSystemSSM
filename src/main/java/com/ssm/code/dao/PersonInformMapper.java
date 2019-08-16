package com.ssm.code.dao;

import com.ssm.code.pojo.Area;
import com.ssm.code.pojo.Build;
import com.ssm.code.pojo.PersonInform;
import com.ssm.code.pojo.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PersonInformMapper {

    /**
     * 获取所有区域
     * @return
     */
    List<Area> getAllArea();

    /**
     * 通过区域获取所有栋数
     * @param code
     * @return
     */
    List<Build> getAllBuildByArea(@Param("code") int code);

    /**
     * 用于完善个人信息
     * @param personInform
     * @return
     */
    int improveInform(@Param("personInform")PersonInform personInform);

    /**
     * 用于判断旧密码是否输入正确
     * @param oldPassword
     * @param stuId
     * @return
     */
    int judgePassword(@Param("oldPassword") String oldPassword,@Param("stuId") String stuId);

    /**
     * 用于修改密码
     * @param stuId
     * @param newPassword
     * @return
     */
    int modifyPassword(@Param("stuId") String stuId,@Param("newPassword") String newPassword);

    /**
     * 获取第一次注册的时间戳
     * @param stuId
     * @return
     */
    List<String> getTime(@Param("stuId") String stuId);

    /**
     * 获取学生的联系方式及联系地址
     */
    Student getStudent(String stuId);
}
