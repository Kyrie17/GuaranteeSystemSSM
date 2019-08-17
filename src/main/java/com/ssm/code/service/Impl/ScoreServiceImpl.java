package com.ssm.code.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.code.dao.ScoreMapper;
import com.ssm.code.pojo.AnswerScore;
import com.ssm.code.pojo.Score;
import com.ssm.code.service.ScoreService;
import com.ssm.code.tools.CommonUtils;
import com.ssm.code.tools.Constant;
import com.ssm.code.tools.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ScoreServiceImpl class
 *
 * @author Flc
 * @date 2019/5/28
 */
@Service
public class ScoreServiceImpl implements ScoreService {
    @Autowired
    ScoreMapper scoreMapper;

    /**
     * 插入评价
     * @param score
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertScore(Score score) {
        //评价时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String now = sdf.format(new Date());
        score.setTime(now);
        String addressID=score.getSatisfied();

        /**
         * 获取满意的程度
         */
        if(addressID.equals("1")) {
            score.setSatisfied("不满意");
        }else if(addressID.equals("2")) {
            score.setSatisfied("一般");
        }else if(addressID.equals("3")) {
            score.setSatisfied("非常满意");
        }

        //插入数据
        int n=scoreMapper.insertScore(score);
        if(n>0){
            return 1;
        }else{
            return -1;
        }
    }

    @Override
    public AnswerScore getScoreByRM(String token, String pageNumStr) {

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
        List<Score> list = scoreMapper.getScoreByRM(repairMan);
        PageInfo<Score> pageInfo = new PageInfo<>(list, 5);

        int currentPage = pageInfo.getPageNum();
        //获取总页数
        int totalPage = pageInfo.getPages();
        //总记录数
        long total = pageInfo.getTotal();
        //封装对象
        return new AnswerScore(list, currentPage, totalPage, total);
    }

    @Override
    public double getAvgByRM(String token) {

        String repairMan = "";
        try {
            repairMan = CommonUtils.parseJWT(token).getSubject();
        } catch (Exception e) {
            return 0;
        }

        double m = scoreMapper.getAvgByRM(repairMan);

        return m;
    }
}
