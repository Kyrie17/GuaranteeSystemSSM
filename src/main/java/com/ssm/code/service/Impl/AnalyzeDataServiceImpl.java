package com.ssm.code.service.Impl;

import com.ssm.code.dao.AnalyzeDataMapper;
import com.ssm.code.service.AnalyzeDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * AnalyzeDataServiceImpl class
 *
 * @author Flc
 * @date 2019/5/25
 */
@Service
public class AnalyzeDataServiceImpl implements AnalyzeDataService {
    @Autowired
    AnalyzeDataMapper analyzeDataMapper;


    /**
     * 用于获取主页的数据分析
     * @return
     */
    @Override
    public int[] getHomeAnalyzeData() {
        int[] analyze=new int[7];
        try {
            analyze[0] = analyzeDataMapper.getSertype(1);
            analyze[1] = analyzeDataMapper.getSertype(2);
            analyze[2] = analyzeDataMapper.getSertype(3);
            analyze[3] = analyzeDataMapper.getSertype(4);
            analyze[4] = analyzeDataMapper.getJudgestate(-1);
            analyze[5] = analyzeDataMapper.getJudgestate(1);
            analyze[6] = analyzeDataMapper.getJudgestate(2);
        }catch (Exception e){
            e.printStackTrace();
        }
        return analyze;
    }


    /**
     * 用于获取报修类型
     * @return
     */
    @Override
    public int[] getSertype() {
        int[] analyze = new int[4];
        try {
            analyze[0] = analyzeDataMapper.getSertype(1);
            analyze[1] = analyzeDataMapper.getSertype(2);
            analyze[2] = analyzeDataMapper.getSertype(3);
            analyze[3] = analyzeDataMapper.getSertype(4);
        }catch (Exception e){
            e.printStackTrace();
        }
        return analyze;
    }


    /**
     * 用于获取维修状态
     * @return
     */
    @Override
    public int[] getJudgestate() {
        int[] analyze=new int[3];
        analyze[0] = analyzeDataMapper.getJudgestate(1);
        analyze[1] = analyzeDataMapper.getJudgestate(2);
        analyze[2] = analyzeDataMapper.getJudgestate(3);
        return analyze;
    }

    /**
     * 用于获取报修时间
     * @return
     */
    @Override
    public int[] getSertime() {
        int[] analyze=new int[6];
        analyze[0] = analyzeDataMapper.getSertime("2018-06");
        analyze[1] = analyzeDataMapper.getSertime("2018-07");
        analyze[2] = analyzeDataMapper.getSertime("2018-08");
        analyze[3] = analyzeDataMapper.getSertime("2018-09");
        analyze[4] = analyzeDataMapper.getSertime("2018-10");
        analyze[5] = analyzeDataMapper.getSertime("2018-11");
        return analyze;
    }


    /**
     * 用于获取维修工的类型
     * @return
     */
    @Override
    public int[] getMajor() {
        int[] analyze=new int[4];
        analyze[0] = analyzeDataMapper.getMajor(1);
        analyze[1] = analyzeDataMapper.getMajor(2);
        analyze[2] = analyzeDataMapper.getMajor(3);
        analyze[3] = analyzeDataMapper.getMajor(4);
        return analyze;
    }
}
