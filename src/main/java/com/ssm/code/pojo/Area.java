package com.ssm.code.pojo;

/**
 * Area class
 *
 * @author Flc
 * @date 2019/5/27
 */
public class Area {
    /**
     *  区域号
     */
    private int id;
    /**
     * 区域名
     */
    private String name;
    /**
     * 区域编码
     */
    private int code;

    public Area(int id, String name, int code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Area() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
