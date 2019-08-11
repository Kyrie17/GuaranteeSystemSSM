package com.ssm.code.common;

import com.ssm.code.tools.CommonUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * GetCookie class
 *
 * @author Flc
 * @date 2019/5/28
 */
public class GetCookie {
    public static String getStuCookie(HttpServletRequest request) {
        String stuId;
        String token="";
        try {
            //获得当前用户的学号
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    switch (cookie.getName()) {
                        case "cookie":
                            token = cookie.getValue();
                            break;
                        default:
                            break;
                    }
                }
            }
            stuId = CommonUtils.parseJWT(token).getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return stuId;
    }
}
