package com.ssm.code.controller;

import com.ssm.code.common.GetCookie;
import com.ssm.code.pojo.Commidity;
import com.ssm.code.service.CommidityService;
import com.ssm.code.tools.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CommidityController class
 *
 * @author Flc
 * @date 2019/5/25
 */
@Controller
@RequestMapping("/commidity")
public class CommidityController {
    @Autowired
    CommidityService commidityService;

    /**
     * 用于主页购买商品
     * @param commidity
     * @param request
     * @return
     */
    @RequestMapping(value = "/homeCommidity",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> purchaseCommidity(@RequestBody Commidity commidity, HttpServletRequest request) {
        String stuId = "";
        Map<String, String> map = new HashMap<>();
        stuId=GetCookie.getStuCookie(request);
        if(stuId==null){
            map.put("msg", "添加购物车失败");
            return map;
        }
        //购买商品
        int answer = commidityService.HomepurchaseCommidity(commidity, stuId);
        if (answer == 1) {
            map.put("msg", "添加购物车成功");
        } else {
            map.put("msg", "添加购物车失败");
        }
        return map;
    }

    /**
     *用于显示购物车的物品
     * @param token
     * @return
     */
    @RequestMapping("/personCommidity")
    @ResponseBody
    public List<Commidity> personCommidity(@RequestParam("cookie") String token) {
        if (token.trim().length() == 0 || token == null) {
            return null;
        }
        String stuId;
        try {
            stuId = CommonUtils.parseJWT(token).getSubject();
        }catch (Exception e){
            return null;
        }
        //获得购物车的所有物品
        List<Commidity> answer=commidityService.getAllPersonCommidity(stuId);
        return answer;
    }


    /**
     * 用于清空购物车
     * @param token
     * @return
     */
    @RequestMapping("/deleteChart")
    @ResponseBody
    public int deleteChart(@RequestParam("cookie") String token){
        if (token.trim().length() == 0 || token == null) {
            return -1;
        }
        String stuId;
        try {
            stuId = CommonUtils.parseJWT(token).getSubject();
        }catch (Exception e){
            return -1;
        }
        //用于清空购物车
        int answer=commidityService.deleteChart(stuId);
        if(answer>0){
            return 1;
        }else{
            return -1;
        }
    }


    /**
     * 用于单个商品的删除
     * @param token
     * @param commidityName
     * @return
     */
    @RequestMapping("/deleteCommidity")
    @ResponseBody
    public int deleteCommidity(@RequestParam("cookie") String token,@RequestParam("commidityName")String commidityName) {
        if (token.trim().length() == 0 || token == null) {
            return -1;
        }
        String stuId;
        try {
            stuId = CommonUtils.parseJWT(token).getSubject();
        } catch (Exception e) {
            return -1;
        }
        //删除单个商品
        int answer = commidityService.deleteCommidity(stuId, commidityName);
        return answer;
    }
}
