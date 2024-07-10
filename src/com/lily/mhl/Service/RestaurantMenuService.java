package com.lily.mhl.Service;

import com.lily.mhl.DAO.RestaurantMenuDAO;
import com.lily.mhl.Domain.RestaurantMenu;

import java.util.List;

/**
 * @author Lily
 * @version 1.0
 */
public class RestaurantMenuService {
    private RestaurantMenuDAO restaurantMenuDAO = new RestaurantMenuDAO();

    public List<RestaurantMenu> getRestaurantMenuList() {//返回菜单

        return restaurantMenuDAO.queryMulti("select mid,name,price from restaurantmenu", RestaurantMenu.class);
    }

    public RestaurantMenu getRestaurantMenuById(int mid) {//根据id返回菜品
        return restaurantMenuDAO.querySingle("select * from restaurantmenu where mid = ?", RestaurantMenu.class, mid);
    }
}
