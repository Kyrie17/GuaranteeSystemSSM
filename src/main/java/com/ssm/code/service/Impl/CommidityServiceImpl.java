package com.ssm.code.service.Impl;

import com.ssm.code.dao.CommidityMapper;
import com.ssm.code.pojo.Commidity;
import com.ssm.code.service.CommidityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * CommidityServiceImpl class
 *
 * @author Flc
 * @date 2019/5/26
 */
@Service
public class CommidityServiceImpl implements CommidityService {

    @Autowired
    CommidityMapper commidityMapper;

    /**
     * 首页购买商品
     * @param commidity
     * @param stuId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int HomepurchaseCommidity(Commidity commidity,String stuId) {
        try {
            //查看该商品是否已经买过
            List<Integer> list = commidityMapper.judgeIfExist(stuId, commidity.getCommidityId());
            int judge;
            if(list.size()>0){
                judge=list.get(0);
            }else{
                judge=0;
            }
            int answer = 0;
            if (judge > 0) {
                //商品数量+1
                answer = commidityMapper.addCart(stuId, judge+1, commidity.getCommidityId());
            } else {
                //添加新的商品
                answer = commidityMapper.insertCart(stuId, 1, commidity);
            }
            if (answer > 0) {
                return 1;
            } else {
                return -1;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * 获取用户的所有商品
     * @param stuId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Commidity> getAllPersonCommidity(String stuId) {
        //获取该用户的所有商品
        List<Commidity> list=commidityMapper.getAllPersonCommidity(stuId);
        return list;
    }

    /**
     * 清空购物车
     * @param stuId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteChart(String stuId){
        //清空购物车内的所有商品
        int answer=commidityMapper.deleteChart(stuId);
        return answer;
    }

    /**
     * 减少商品
     * @param stuId
     * @param commidityName
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteCommidity(String stuId, String commidityName) {
        //查看该商品的数量
        List<Integer> answer=commidityMapper.commidityCount(stuId,commidityName);
        int num;
        if(answer.size()>0){
            num=answer.get(0);
            if(num==1){
                //清空购物车
                int answer1=commidityMapper.deletecommidity(stuId,commidityName);
                if(answer1>0){
                    return 1;
                }
            }else{
                //减少商品的数量
                int answer2=commidityMapper.decreasecommidity(stuId,commidityName);
                if(answer2>0){
                    return 1;
                }
            }
        }
        return -1;

    }


}
