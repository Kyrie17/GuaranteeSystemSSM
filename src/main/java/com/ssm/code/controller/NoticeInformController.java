package com.ssm.code.controller;

import com.ssm.code.pojo.AnswerNoticeInform;
import com.ssm.code.pojo.NoticeInform;
import com.ssm.code.service.NoticeInformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * NoticeInformController class
 *
 * @author Flc
 * @date 2019/5/24
 */
@Controller
@RequestMapping("/getNoticeInform")
public class NoticeInformController {

    @Autowired
    NoticeInformService noticeInformService;

    /**
     * 用于获取主页的服务信息
     * @return
     */
    @RequestMapping("/homepage")
    @ResponseBody
    public List<NoticeInform> getHomePageNotice(){
        List<NoticeInform> noticeInforms=noticeInformService.getHomePageNotice();
        return noticeInforms;
    }


    /**
     * 用于获取更多的服务信息
     * @param page
     * @return
     */
    @RequestMapping("/moreNotice")
    @ResponseBody
    public AnswerNoticeInform moreMoreNotice(@RequestParam("pageNum")int page){
        AnswerNoticeInform moreNoticeInforms=noticeInformService.getMoreNotice(page);
        return moreNoticeInforms;
    }


    /**
     * 用于下载服务信息
     * @param request
     * @param response
     */
    @RequestMapping("/loadNoticeInform")
    @ResponseBody
    public void loadNoticeInform( HttpServletRequest request, HttpServletResponse response){
        //获取文件名
        String fileName=request.getParameter("fileName");
        if(fileName==null){
            fileName="";
        }
        String filePath="";
        filePath=noticeInformService.findPath(fileName);
        if(filePath==""){
            return;
        }
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        try {
            File f = new File(filePath);
            System.out.println(filePath);
            FileInputStream in = new FileInputStream(f);
            OutputStream out = response.getOutputStream();
            int n = 0;
            byte[] b = new byte[500];
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            out.close();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("发生错误");
        }
    }


}
