package com.ssm.code.pojo;

/**
 * PersonInform class
 *
 * @author Flc
 * @date 2019/5/27
 */
public class PersonInform {
    /**
     * 房间号
     */
    private String room;
    /**
     * 性别
     */
    private int sex;
    /**
     * 区域
     */
    private String area;
    /**
     * 栋数
     */
    private String build;
    /**
     * 学号
     */
    private String stuId;

    public PersonInform() {
    }

    public PersonInform(String room, int sex, String area, String build, String stuId) {
        this.room = room;
        this.sex = sex;
        this.area = area;
        this.build = build;
        this.stuId = stuId;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
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

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }
}
