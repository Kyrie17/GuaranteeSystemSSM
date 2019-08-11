package com.ssm.code.dao;

import com.ssm.code.pojo.Commidity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommidityMapper {
    /**
     * 查看购物车中是否已经有该商品
     * @param stuId
     * @param id
     * @return
     */
    List<Integer> judgeIfExist(@Param("stuId") String stuId, @Param("id") String id);

    /**
     * 添加到购物车
     * @param stuId
     * @param num
     * @param id
     * @return
     */
    int addCart(@Param("stuId") String stuId,@Param("num") int num,@Param("id") String id);

    /**
     * 添加购物车信息
     * @param stuId
     * @param judge
     * @param commidity
     * @return
     */
    int insertCart(@Param("stuId") String stuId, @Param("judge") int judge, @Param("commidity") Commidity commidity);

    /**
     * 获得所有商品
     * @param stuId
     * @return
     */
    List<Commidity> getAllPersonCommidity(@Param("stuId") String stuId);

    /**
     * 清空购物车
     * @param stuId
     * @return
     */
    int deleteChart(@Param("stuId")String stuId);

    /**
     * 计算商品的数量
     * @param stuId
     * @param commidityName
     * @return
     */
    List<Integer> commidityCount(@Param("stuId") String stuId,@Param("commidityName") String commidityName);

    /**
     * 清空商品
     * @param stuId
     * @param commidityName
     * @return
     */
    int deletecommidity(@Param("stuId") String stuId,@Param("commidityName") String commidityName);

    /**
     * 商品数目-1
     * @param stuId
     * @param commidityName
     * @return
     */
    int decreasecommidity(@Param("stuId") String stuId,@Param("commidityName") String commidityName);
}
