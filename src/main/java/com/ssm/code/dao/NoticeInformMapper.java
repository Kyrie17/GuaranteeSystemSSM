package com.ssm.code.dao;

import com.ssm.code.pojo.NoticeInform;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NoticeInformMapper class
 *
 * @author Flc
 * @date 2019/5/24
 */
@Repository
public interface NoticeInformMapper {
    /**
     * 用于查询首页的服务信息
     * @param num
     * @return
     */
    List<NoticeInform> getHomePageNotice(@Param("num") int num);

    /**
     * 用于获取所有的服务信息
     * @return
     */
    List<NoticeInform> getAllNotice();

    /**
     * 用于查找文件的路径
     * @param fileName
     * @return
     */
    String findPath(@Param("fileName") String fileName);
}
