package com.ssm.code.pojo;

/**
 * Build class
 *
 * @author Flc
 * @date 2019/5/27
 */
public class Build {
    /**
     * 获取对应的栋数号
     */
    private int id;
    /**
     * 获取栋数
     */
    private String building;
    /**
     * 对应的区域编码
     */
    private int code;

    public Build() {
    }

    public Build(int id, String building, int code) {
        this.id = id;
        this.building = building;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
