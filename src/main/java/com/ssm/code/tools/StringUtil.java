package com.ssm.code.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

    static final Pattern pattern = Pattern.compile("[1-9]{1}\\d*");

    /**
     * 校验字符串是否是大于0的数字
     * @param string
     * @return
     */
    public static boolean isNum(String string){
        Matcher matcher = pattern.matcher(string);
        return matcher.matches();
    }

}
