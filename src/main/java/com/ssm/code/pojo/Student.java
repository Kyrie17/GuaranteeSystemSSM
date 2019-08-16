package com.ssm.code.pojo;

public class Student {

    /**
     * 学生Id
     */
    private String stuId;

    /**
     * 学生联系方式
     */
    private String phone;

    /**
     * 学生住址
     */
    private String area;

    private String build;

    private String room;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBuild() {
        return build;
    }

    public void setBuild(String build) {
        this.build = build;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
