package com.ssm.code.service;

import com.ssm.code.pojo.AnswerNoticeInform;
import com.ssm.code.pojo.NoticeInform;

import java.util.List;

/**
 * NoticeInformService class
 *
 * @author Flc
 * @date 2019/5/24
 */
public interface NoticeInformService {

    /**
     * 用于获取主页的服务信息
     * @return
     */
    List<NoticeInform> getHomePageNotice();


    /**
     * 用于获取更多的服务信息
     * @param page
     * @return
     */
    AnswerNoticeInform getMoreNotice(int page);


    /**
     * 用于查找文件的路径
     * @param fileName
     * @return
     */
    String findPath(String fileName);
}
