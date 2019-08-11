package com.ssm.code.service.Impl;

import com.ssm.code.dao.ScoreMapper;
import com.ssm.code.pojo.Score;
import com.ssm.code.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

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
}
