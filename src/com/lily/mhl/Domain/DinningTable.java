package com.lily.mhl.Domain;

/**
 * @author Lily
 * @version 1.0
 * (id int primary key auto_increment,
 * state varchar(20) not null default '',
 * orderName varchar(50) not null default '',
 * orderTel varchar(20) not null default ''
 */
public class DinningTable {
    private Integer id;
    private String state;
    private String orderName;
    private String orderTel;

    public DinningTable() {
    }

    public DinningTable(Integer id, String state, String orderName, String orderTel) {
        this.id = id;
        this.state = state;
        this.orderName = orderName;
        this.orderTel = orderTel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel;
    }
}


