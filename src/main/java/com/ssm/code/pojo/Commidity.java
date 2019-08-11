package com.ssm.code.pojo;

/**
 * Commidity class
 *
 * @author Flc
 * @date 2019/5/26
 */
public class Commidity {
    /**
     * 商品名字
     */
    private String commidityName;
    /**
     * 商品编号
     */
    private String commidityId;
    /**
     * 商品单价
     */
    private double commidityPrice;
    /**
     * 商品数量
     */
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Commidity() {
    }

    public Commidity(String commidityName, double commidityPrice, int quantity) {
        this.commidityName = commidityName;
        this.commidityPrice = commidityPrice;
        this.quantity = quantity;
    }
    public Commidity(String commidityName, String commidityId, double commidityPrice, int quantity) {
        this.commidityName = commidityName;
        this.commidityId = commidityId;
        this.commidityPrice = commidityPrice;
        this.quantity = quantity;
    }
    public Commidity(String commidityName, String commidityId,  int quantity,double commidityPrice) {
        this.commidityName = commidityName;
        this.commidityId = commidityId;
        this.commidityPrice = commidityPrice;
        this.quantity = quantity;
    }


    public String getCommidityName() {
        return commidityName;
    }

    public void setCommidityName(String commidityName) {
        this.commidityName = commidityName;
    }

    public String getCommidityId() {
        return commidityId;
    }

    public void setCommidityId(String commidityId) {
        this.commidityId = commidityId;
    }

    public double getCommidityPrice() {
        return commidityPrice;
    }

    public void setCommidityPrice(double commidityPrice) {
        this.commidityPrice = commidityPrice;
    }
}
