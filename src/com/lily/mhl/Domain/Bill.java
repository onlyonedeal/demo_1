package com.lily.mhl.Domain;

/**
 * @author Lily
 * @version 1.0
 * id int primary key auto_increment,
 *                    billId varchar(50) not null default '',
 *                    menuName varchar(32) not null default 0,
 *                    nums int not null default 0,
 *                    diningTableId int not null  default 0,
 *                    money double not null default 0,
 *                     billDate DATETIME not null,
 *                     state varchar(32) not null default ''
 */
public class Bill {
    private int id;
    private String billId;
    private String menuName;
    private int nums;
    private int diningTableId;
    private double money;
    private String billDate;
    private String state;

    public Bill() {
    }

    public Bill(int id, String billId, String menuName, int nums, int diningTableId, double money, String billDate, String state) {
        this.id = id;
        this.billId = billId;
        this.menuName = menuName;
        this.nums = nums;
        this.diningTableId = diningTableId;
        this.money = money;
        this.billDate = billDate;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public int getNums() {
        return nums;
    }

    public void setNums(int nums) {
        this.nums = nums;
    }

    public int getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(int diningTableId) {
        this.diningTableId = diningTableId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
