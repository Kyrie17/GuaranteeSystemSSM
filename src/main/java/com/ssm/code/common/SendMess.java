package com.ssm.code.common;


import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * SendMess class
 *
 * @author Flc
 * @date 2019/5/22
 */
public class SendMess {

    /**
     * 秒滴科技
     */
    private final long serialVersionUID = 1L;
    private final String QUERY_PATH = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
    private final String ACCOUNT_SID = "aa1959e2ee8e4607b8e77d13cc88f958";
    private final String AUTH_TOKEN = "a5de25d2a4b94e1098ab5be4df9bd2d2";
    private String rod = "123";
    //smsCode();

    /**
     * 根据手机号发送验证码
     * @param phone
     * @throws Exception
     */
    public void SendMessage(String phone) throws Exception{
        getCode(phone);
    }

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    public String getCode(String phone) {
        String timestamp = getTimestamp();
        String sig = getMD5(ACCOUNT_SID, AUTH_TOKEN, timestamp);
        String tamp = "【广东金融学院】您的验证码为" + rod + "，请于1分钟内正确输入，如非本人操作，请忽略此短信。";
        OutputStreamWriter out = null;
        BufferedReader br = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(QUERY_PATH);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            // 设置是否允许数据写入
            connection.setDoInput(true);
            // 设置是否允许参数数据输出
            connection.setDoOutput(true);
            // 设置链接响应时间
            connection.setConnectTimeout(5000);
            // 设置参数读取时间
            connection.setReadTimeout(10000);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            // 提交请求
            out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            String args = getQueryArgs(ACCOUNT_SID, tamp, phone, timestamp, sig, "JSON");
            out.write(args);
            out.flush();
            // 读取返回参数

            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                result.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        JSONObject json = JSONObject.parseObject(result.toString());
        String respCode = json.getString("respCode");
        String defaultRespCode = "00000";
        if (defaultRespCode.equals(respCode)) {
            return rod;
        } else {
            return defaultRespCode;
        }
    }

    /**
     * 定义一个请求参数拼接方法
     */
    private String getQueryArgs(String accountSid, String smsContent, String to, String timestamp, String sig,
                                      String respDataType) {
        return "accountSid=" + accountSid + "&smsContent=" + smsContent + "&to=" + to + "&timestamp=" + timestamp
                + "&sig=" + sig + "&respDataType=" + respDataType;
    }

    /**
     * 获取时间戳
      * @return
     */
    private String getTimestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }

    /**
     * sing签名
     * @param sid
     * @param token
     * @param timestamp
     * @return
     */
    private String getMD5(String sid, String token, String timestamp) {

        StringBuilder result = new StringBuilder();
        String source = sid + token + timestamp;
        // 获取某个类的实例
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            // 要进行加密的东西
            byte[] bytes = digest.digest(source.getBytes());
            for (byte b : bytes) {
                String hex = Integer.toHexString(b & 0xff);
                if (hex.length() == 1) {
                    result.append("0" + hex);
                } else {
                    result.append(hex);
                }
            }
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result.toString();
    }

    /**
     * 创建验证码
     * @return
     */
    private String smsCode() {
        String random = (int) ((Math.random() * 9 + 1) * 100000) + "";
        return random;
    }

    /**
     * 获取验证码
     */
    public String getRod(){
        return rod;
    }
}
