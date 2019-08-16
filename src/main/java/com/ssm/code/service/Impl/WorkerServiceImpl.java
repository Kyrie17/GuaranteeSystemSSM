package com.ssm.code.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.code.dao.WorkerMapper;
import com.ssm.code.pojo.AnswerRepairFrom;
import com.ssm.code.pojo.RepairForm;
import com.ssm.code.service.WorkerService;
import com.ssm.code.tools.CommonUtils;
import com.ssm.code.tools.Constant;
import com.ssm.code.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerServiceImpl implements WorkerService {

    @Autowired
    WorkerMapper workerMapper;

    @Override
    public AnswerRepairFrom getRepairManRF(String token, String pageNumStr, String judgeState, String userConfirm) {

        String repairMan = "";
        try {
            repairMan = CommonUtils.parseJWT(token).getSubject();
        } catch (Exception e) {
            return null;
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
        List<RepairForm> list = workerMapper.getRFByRepairMan(repairMan);
        PageInfo<RepairForm> pageInfo = new PageInfo<>(list, 10);

        int currentPage = pageInfo.getPageNum();
        //获取总页数
        int totalPage = pageInfo.getPages();
        //总记录数
        long total = pageInfo.getTotal();
        //封装对象
        return new AnswerRepairFrom(list, currentPage, totalPage, total);
    }

    @Override
    public int changeJudgeState(int orderNumber, int judgeState) {

        int m = 0;
        try {
            m = 0;
            if(judgeState == -1) {
                judgeState = 1;
                m = workerMapper.changeJudgeState(judgeState, orderNumber);
            }else if(judgeState == 1){
                judgeState = 2;
                m = workerMapper.changeJudgeState(judgeState, orderNumber);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }
}
