package com.lily.mhl.Domain;

/**
 * @author Lily
 * @version 1.0、
 * id int primary key auto_increment,
 *     mid int not null,
 *     name varchar(50) not null,
 *     price int not null
 */
public class RestaurantMenu {
    private Integer id;
    private Integer mid;
    private String name;
    private Double price;

    public RestaurantMenu() {
    }

    public RestaurantMenu(Integer id, Integer mid, String name, Double price) {
        this.id = id;
        this.mid = mid;
        this.name = name;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
