package com.lily.mhl.Service;

import com.lily.mhl.DAO.DinningTableDAO;
import com.lily.mhl.Domain.DinningTable;

import java.util.List;

/**
 * @author Lily
 * @version 1.0
 */
public class DinningTableService {
    private DinningTableDAO dinningTableDAO = new DinningTableDAO();

    public List<DinningTable> getDinningTableList() {//查询餐桌状态
        return dinningTableDAO.queryMulti("select id , state from diningtable", DinningTable.class);
    }

    public DinningTable getDinningTableById(int id) {//根据id查询餐桌如果返回空则餐桌不存在 如果状态不为空则餐桌已被预定
        return dinningTableDAO.querySingle("select * from diningtable where id = ?", DinningTable.class, id);
    }

    public boolean orderDinningTable(int id, String name, String phone) {//预定餐桌

        return dinningTableDAO.update("update diningtable set state = '已被预订',orderName = ?,orderTel = ? where id = ?", name, phone, id)>0;
    }

    public boolean cancelDinningTableState(int id) {//取消预订
        return dinningTableDAO.update("update diningtable set state = '空',orderName = '',orderTel = '' where id = ?", id)>0;
    }

    public boolean updateDiningTableState(int id, String state) {//修改餐桌状态
    return dinningTableDAO.update("update diningtable set state = ? where id = ?", state, id)>0;
    }
}