package com.ssm.code.pojo;

/**
 * NoticeInform class
 *
 * @author Flc
 * @date 2019/5/24
 */
public class NoticeInform {
    /**
     * 文件名
     */
    private String fileName;
    /**
     * 上传时间
     */
    private String upLoadDate;
    /**
     * 下载数
     */
    private int downLoadNum;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUpLoadDate() {
        return upLoadDate;
    }

    public void setUpLoadDate(String upLoadDate) {
        this.upLoadDate = upLoadDate;
    }

    public int getDownLoadNum() {
        return downLoadNum;
    }

    public void setDownLoadNum(int downLoadNum) {
        this.downLoadNum = downLoadNum;
    }
}
