package com.ssm.code.common;

import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Random;

/**
 * ImageMess class
 *
 * @author Flc
 * @date 2019/5/23
 */
public class ImageMess {
    private  StringBuffer sb=new StringBuffer();
    private StringBuilder mess=new StringBuilder("");

    public String getIamgeMess()throws Exception{
        /*
         * 建立图象缓冲区
         * 建立绘制图片的对象 Graphics
         * 获取颜色
         * 设置图片位置及大小
         */
        BufferedImage bi=new BufferedImage(70,40,BufferedImage.TYPE_INT_BGR);
        Graphics g=bi.getGraphics();
        Random r=new Random();
        Color c=new Color(248,246,231);
        g.setColor(c);
        g.fillRect(0, 0, 70, 40);
        /*
         * 生成随机产生字符范围
         * 新建随机数对象，在所给字符串长度内生成随机数，通过对应位置读取对应字符
         * 建立验证码字符串对象，并添加至4个（设置验证码为4个字符）
         */
        char ch[]="abcdefghijklmn123456789".toCharArray();


        int len=ch.length;
        for(int i=0;i<4;i++) {
            int index=r.nextInt(len);
            //随机设置当前字符的颜色
            g.setColor(new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255)));
            g.setFont(new Font(ch[index]+"", 10, 18));
            g.drawString(ch[index]+"", i*15+9, 20);
            sb.append(ch[index]);
            mess.append(ch[index]);
        }
        /*
         * 保存当前验证码字符串
         * 绘制对应验证码的图象
         */
        //io流
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //写入流中
        ImageIO.write(bi, "png", baos);
        //转换成字节
        byte[] bytes = baos.toByteArray();
        BASE64Encoder encoder = new BASE64Encoder();
        //转换成base64串
        String png_base64 =  encoder.encodeBuffer(bytes);
        //删除 \r\n
        png_base64 = png_base64.replaceAll("\n", "").replaceAll("\r", "");
        return png_base64;
    }

    public String getMess(){
        return mess.toString();
    }
}
