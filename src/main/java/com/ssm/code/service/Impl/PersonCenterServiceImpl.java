package com.ssm.code.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.code.pojo.RepairForm;
import com.ssm.code.tools.DateMinusUtil;
import com.ssm.code.tools.StringUtil;
import com.ssm.code.dao.RepairFormMapper;
import com.ssm.code.pojo.AnswerRepairFrom;
import com.ssm.code.service.PersonCenterService;
import com.ssm.code.tools.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PersonCenterServiceImpl implements PersonCenterService {

    @Autowired
    RepairFormMapper repairFormMapper;

    @Override
    public AnswerRepairFrom getPersonalRF(String username, String pageNumStr, int judgeState, int userConfirm) {

        List<RepairForm> repairForms = null;
        PageInfo<RepairForm> pageInfo = null;

        // 校验pageNum参数输入合法性
        if (pageNumStr != null && !StringUtil.isNum(pageNumStr)) {
            return null;
        }

        //显示第几页数据
        int pageNum = Constant.DEFAULT_PAGE_NUM;
        if (pageNumStr != null && !"".equals(pageNumStr.trim())) {
            pageNum = Integer.parseInt(pageNumStr);
        }

        // 每页显示多少条记录
        int pageSize = Constant.DEFAULT_PAGE_SIZE;

        if (judgeState != 2) {    //查询未完成的报修单
            //使用pageHelper插件
            PageHelper.startPage(pageNum, Constant.DEFAULT_PAGE_SIZE);
            repairForms = repairFormMapper.getUnfinishedRF(username);
            pageInfo = new PageInfo<>(repairForms, 5);
        } else {
            if (userConfirm == 1) {    //报修单审核通过，用户已确定
                PageHelper.startPage(pageNum, Constant.DEFAULT_PAGE_SIZE);
                repairForms = repairFormMapper.getFinishedRF(username, userConfirm);
                pageInfo = new PageInfo<>(repairForms, 5);
            } else {
                //报修单审核通过，用户未确定
                repairForms = repairFormMapper.getFinishedRF(username, userConfirm);

                //当前时间
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String now = sdf.format(new Date());
                long endT = DateMinusUtil.fromDateStringToLong(now);

                if (repairForms == null) {    //返回结果为空
                } else {    //计算如果当前时间与维修工确定完成时间超过15天
                    for (int i = 0; i < repairForms.size(); i++) {

                        long startT = DateMinusUtil.fromDateStringToLong(repairForms.get(i).getSubmitTime());

                        long ss = (endT - startT) / 1000; // 共计秒数

                        if (ss >= 1296000) {//如果超过15天,学生端自动确认报修单完成
                            int orderNumber = repairForms.get(i).getOrderNumber();
                            repairFormMapper.ChangeUserConfirm(orderNumber);
                        }
                    }
                    PageHelper.startPage(pageNum, Constant.DEFAULT_PAGE_SIZE);
                    repairForms = repairFormMapper.getFinishedRF(username, userConfirm);
                    pageInfo = new PageInfo<>(repairForms, 5);
                }
            }
        }
        int currentPage = pageInfo.getPageNum();
        //获取总页数
        int totalPage = pageInfo.getPages();
        //总记录数
        long total = pageInfo.getTotal();
        //封装对象
        return new AnswerRepairFrom(repairForms, currentPage, totalPage, total);
    }

    @Override
    public RepairForm getRFByOrderNum(String orderNumber) {
        return repairFormMapper.getRFByOrderNum(orderNumber);
    }

    @Override
    public int ChangeUserConfirm(int orderNumber) {
        return repairFormMapper.ChangeUserConfirm(orderNumber);
    }
}
