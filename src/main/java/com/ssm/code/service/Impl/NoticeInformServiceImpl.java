package com.ssm.code.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.code.dao.NoticeInformMapper;
import com.ssm.code.pojo.AnswerNoticeInform;
import com.ssm.code.pojo.NoticeInform;
import com.ssm.code.service.NoticeInformService;
import com.ssm.code.tools.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * NoticeInfromImpl class
 *
 * @author Flc
 * @date 2019/5/24
 */
@Service
public class NoticeInformServiceImpl implements NoticeInformService {

    @Autowired
    NoticeInformMapper noticeInformMapper;

    /**
     * 用于获取首页的服务信息
     *
     * @return
     */
    @Override
    public List<NoticeInform> getHomePageNotice() {
        List<NoticeInform> noticeInforms = null;
        try {
            noticeInforms = noticeInformMapper.getHomePageNotice(Constant.DEFAULT_NoticeInform_NUM);
        } catch (Exception e) {
            e.printStackTrace();
            noticeInforms = null;
        } finally {
            return noticeInforms;
        }
    }


    /**
     * 用于获取更多的服务信息
     *
     * @param page
     * @return
     */
    @Override
    @Transactional
    public AnswerNoticeInform getMoreNotice(int page) {
        AnswerNoticeInform moreNoticeInforms = null;
        try {
            //使用pageHelper插件
            PageHelper.startPage(page, Constant.DEFAULT_PAGE_SIZE);
            List<NoticeInform> noticeInforms = noticeInformMapper.getAllNotice();
            PageInfo<NoticeInform> pageInfo = new PageInfo<>(noticeInforms, 5);
            //获取当前页码
            int currentPage = pageInfo.getPageNum();
            //获取总页数
            int totalPage = pageInfo.getPages();
            //封装对象
            moreNoticeInforms = new AnswerNoticeInform(noticeInforms, currentPage, totalPage);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return moreNoticeInforms;
        }
    }


    /**
     * 用于查找文件的路径
     *
     * @param fileName
     * @return
     */
    @Override
    public String findPath(String fileName) {
        String path="";
        try {
            path=noticeInformMapper.findPath(fileName);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return path;
        }
    }
}
