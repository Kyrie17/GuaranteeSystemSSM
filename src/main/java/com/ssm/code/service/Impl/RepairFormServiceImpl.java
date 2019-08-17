package com.ssm.code.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.code.common.AutomaticAssign;
import com.ssm.code.common.SavePicture;
import com.ssm.code.dao.RepairFormMapper;
import com.ssm.code.pojo.AnswerRepairFrom;
import com.ssm.code.pojo.RepairForm;
import com.ssm.code.service.RepairFormService;
import com.ssm.code.tools.Constant;
import com.ssm.code.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * RepairServiceImpl class
 * @author Flc
 * @date 2019/8/11
 */
@Service
@Transactional
public class RepairFormServiceImpl implements RepairFormService {

    @Autowired
    RepairFormMapper repairFormMapper;

    @Autowired
    AutomaticAssign automaticAssign;

    @Autowired
    SavePicture savePicture;

    @Override
    public int judgeFinshInform(String stuId) {
        int answer = repairFormMapper.judgeFinshInform(stuId);
        if(answer > 0) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    @Transactional
    public int submitRepairForm(RepairForm repairForm, HttpServletRequest request) {

        int orderNumber = 0;
        int judgeState = -1;
        String repairMan = "";

        //自动分配维修工人
        repairMan = repairMan = automaticAssign.automaticAssign(repairForm.getSerType());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sdf.format(new Date());

        //上传图片
        String fileName = savePicture.savePic(request);
        if (fileName.equals("-1")){ //如果图片上传失败
            return 0;
        }

        //实例化RepairForm
        RepairForm rf = new RepairForm();
        rf.setOrderNumber(orderNumber);
        rf.setUsername(repairForm.getUsername());
        rf.setPhone(repairForm.getPhone());
        rf.setSerType(repairForm.getSerType());
        rf.setSerAdd(repairForm.getSerAdd());
        rf.setSerInform(repairForm.getSerInform());
        rf.setSerTime(repairForm.getSerTime());
        rf.setJudgeState(judgeState);
        rf.setFile_path(fileName);
        rf.setRepairMan(repairMan);
        rf.setDetailTime(repairForm.getDetailTime());

        //将报修单信息保存到数据库
        int m = repairFormMapper.submitRepairForm(rf);

        return m;
    }

    @Override
    public List<RepairForm> getLastestRF() {
        return repairFormMapper.getLastestRF(Constant.DEFAULT_NoticeInform_NUM);
    }

    @Override
    public AnswerRepairFrom getAllRF(String username, String pageNumStr, String serTypeStr, String judgeStateStr) {

        //报修类型
        int serType = Constant.SERTYPE_ALL;
        if(serTypeStr != null && !"".equals(serTypeStr.trim())){
            serType = Integer.parseInt(serTypeStr);
        }

        //报修状态
        int judgeState = Constant.JUDGESTATE_ALL;
        if(judgeStateStr != null && !"".equals(judgeStateStr.trim())){
            judgeState = Integer.parseInt(judgeStateStr);
        }

        // 校验pageNum参数输入合法性
        if(pageNumStr !=null && !StringUtil.isNum(pageNumStr)){
            return null;
        }

        //显示第几页数据
        int pageNum = Constant.DEFAULT_PAGE_NUM;
        if(pageNumStr!=null && !"".equals(pageNumStr.trim())){
            pageNum = Integer.parseInt(pageNumStr);
        }

        PageHelper.startPage(pageNum, Constant.DEFAULT_PAGE_SIZE);
        //获取查询结果
        List<RepairForm> result = repairFormMapper.getAllRF(username, serType, judgeState);
        PageInfo<RepairForm> pageInfo = new PageInfo<>(result, 10);

        int currentPage = pageInfo.getPageNum();
        //获取总页数
        int totalPage = pageInfo.getPages();
        //总记录数
        long total = pageInfo.getTotal();
        //封装对象
        return new AnswerRepairFrom(result, currentPage, totalPage, total);
    }


    @Override
    public int removeRF(String orderNumber) {
        int m = repairFormMapper.removeRF(orderNumber);
        return m;
    }

    @Override
    public int updateRFInfor(String orderNumber, String judgeState, String repairMan) {
        System.out.println("ordernumber" + orderNumber);
        return repairFormMapper.updateRFInfor(orderNumber, judgeState, repairMan);
    }
}
