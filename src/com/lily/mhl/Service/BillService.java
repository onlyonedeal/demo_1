package com.lily.mhl.Service;

import com.lily.mhl.DAO.BillDAO;
import com.lily.mhl.Domain.Bill;

import java.util.List;
import java.util.UUID;

/**
 * @author Lily
 * @version 1.0
 */
public class BillService {
    private BillDAO billDAO = new BillDAO();
    private RestaurantMenuService restaurantMenuService = new RestaurantMenuService();
    private DinningTableService dinningTableService = new DinningTableService();

    public boolean orderMenu(int mid, int nums, int tid) {//点餐
        //生成订单编号
        String uuid = UUID.randomUUID()+"";
        //修改订单 并直接算出订单金额
        int update = billDAO.update("insert into bill values(null,?,?,?,?,?,NOW(),'未付款')", uuid, restaurantMenuService.getRestaurantMenuById(mid).getName(), nums, tid,
                restaurantMenuService.getRestaurantMenuById(mid).getPrice() * nums);
        if (update <= 0) {
            return false;
        }
        //修改餐桌状态
        return dinningTableService.updateDiningTableState(tid, "就餐中");
    }

    public List<Bill> getBillList(int tid){//返回账单表
      return  billDAO.queryMulti("select diningTableId,menuName,nums,money,billDate,state from bill where diningTableId=?", Bill.class, tid);
    }

    public boolean settleAccounts (int tid){//用于结账
        if (billDAO.update("update bill set state='已付款' where diningTableId=?",tid)>0)
        //将餐桌清理
        {
          return dinningTableService.cancelDinningTableState(tid);
        }else{
            return false;
        }
    }

}



