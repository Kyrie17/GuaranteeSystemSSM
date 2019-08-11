package com.ssm.code.service;

import com.ssm.code.pojo.Commidity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommidityService {

    /**
     * 首页购买商品
     * @param commidity
     * @param stuId
     * @return
     */
    int HomepurchaseCommidity(Commidity commidity,String stuId);

    /**
     * 获取所有商品信息
     * @param stuId
     * @return
     */
    List<Commidity> getAllPersonCommidity(String stuId);

    /**
     * 清空购物车
     * @param stuId
     * @return
     */
    public int deleteChart(String stuId);

    /**
     * 减少商品
     * @param stuId
     * @param commidityName
     * @return
     */
    public int deleteCommidity(String stuId,String commidityName);
}
