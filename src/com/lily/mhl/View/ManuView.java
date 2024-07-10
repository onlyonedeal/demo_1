package com.lily.mhl.View;

import com.lily.mhl.Domain.Bill;
import com.lily.mhl.Domain.DinningTable;
import com.lily.mhl.Domain.Employee;
import com.lily.mhl.Domain.RestaurantMenu;
import com.lily.mhl.Service.BillService;
import com.lily.mhl.Service.DinningTableService;
import com.lily.mhl.Service.EmployeeService;
import com.lily.mhl.Service.RestaurantMenuService;
import com.lily.mhl.Utils.Tools;

import java.util.List;

/**
 * @author Lily
 * @version 1.0
 * 主菜单
 */
public class ManuView {
    private boolean loop = true;
    private String key = null;
    private EmployeeService employeeService = new EmployeeService();
    private DinningTableService dinningTableService = new DinningTableService();
    private RestaurantMenuService restaurantMenuService = new RestaurantMenuService();
    private BillService billService = new BillService();

    public static void main(String[] args) {

        new ManuView().mainMenu();
    }

    public void diningTableStatus() {//用于查看餐桌状态
        List<DinningTable> dinningTableList = dinningTableService.getDinningTableList();
        System.out.println("\n餐桌编号\t\t餐桌状态");
        for (DinningTable dinningTable : dinningTableList) {
            System.out.println(dinningTable.getId() + "\t\t\t" + dinningTable.getState());
        }
    }

    public void RestaurantManu() {//用于查看菜单
        List<RestaurantMenu> restaurantMenuList = restaurantMenuService.getRestaurantMenuList();
        System.out.println("\n菜品编号\t\t菜品名称\t\t菜品价格");
        for (RestaurantMenu restaurantMenu : restaurantMenuList) {
            System.out.println("\t" + restaurantMenu.getMid() + "\t\t" + restaurantMenu.getName() + "\t\t" + restaurantMenu.getPrice());
        }

    }

    public void orderTable() {//用于预定餐桌
        System.out.println("请输入要预定的餐桌编号（输入-1取消）：");
        int id = Tools.readInt();
        if (id == -1) {
            System.out.println("取消预订");
            return;
        }
        char c = Tools.readConfirmSelection();
        if (c == 'Y') {
            //判断餐桌状态
            if (dinningTableService.getDinningTableById(id).getId() >= 1
                    && dinningTableService.getDinningTableById(id).getState().equals("空")) {
                System.out.println("请输入您的姓名和电话");
                System.out.print("姓名：");
                String name = Tools.readString(10);
                System.out.print("电话：");
                String tel = Tools.readString(10);
                if (dinningTableService.orderDinningTable(id, name, tel)) {
                    System.out.println("预定成功");
                }
            } else {
                System.out.println("输入的餐桌号有误或该餐桌已被预订");
            }
        } else {
            System.out.println("取消预定");
        }
    }

    public void cancelOrderTable() {//用于取消预定餐桌
        System.out.println("请输入要取消预订的餐桌编号：");
        int id = Tools.readInt();
        //判断餐桌状态
        if (dinningTableService.getDinningTableById(id).getId() >= 1
                && !(dinningTableService.getDinningTableById(id).getState().equals("空"))) {
            System.out.println("请输入您的姓名和电话");
            String name = Tools.readString(10);
            String tel = Tools.readString(10);
            if (dinningTableService.getDinningTableById(id).getOrderName().equals(name)
                    && dinningTableService.getDinningTableById(id).getOrderTel().equals(tel)) {
                if (dinningTableService.cancelDinningTableState(id)) {
                    System.out.println("取消成功");
                }
            } else {
                System.out.println("您的姓名或电话错误");
            }
        } else {
            System.out.println("输入的餐桌号有误或该餐桌未被预订");
        }
    }

    public void orderDishes() {//用于点菜
        System.out.print("请输入要点菜的餐桌编号：");
        int id = Tools.readInt();
        System.out.print("请输入要点的菜品编号：");
        int mid = Tools.readInt();
        System.out.print("请输入要点的份数：");
        int num = Tools.readInt();
        if (billService.orderMenu(mid, num, id)) {
            System.out.println("点菜成功");
        } else {
            System.out.println("点菜失败，您输入的菜名有误或餐桌名有误");
        }
    }

    public void showBill() {//该方法用于查看账单
        System.out.print("请输入您要查看账单的餐桌编号：");
        int id = Tools.readInt();
        List<Bill> billList = billService.getBillList(id);
        if (!billList.isEmpty()) {
            System.out.println("餐桌编号\t\t菜品名称\t\t\t\t\t\t数量\t\t\t\t\t共消费\t\t\t\t\t状态");

            for (Bill bill : billList) {
                System.out.println("\t" + bill.getDiningTableId() + "\t\t" + bill.getMenuName() + "\t\t\t\t\t\t" + bill.getNums() + "\t\t\t\t\t\t" + bill.getMoney()+"\t\t\t\t\t"+bill.getState());
            }
        }
        else{
            System.out.println("您输入的餐桌号码有误");
        }
    }

    public void payBill() {//用于结账
       System.out.print("请输入您要结账的餐桌编号：");
       int id = Tools.readInt();
       if (billService.settleAccounts(id)){
           System.out.println("结账成功");
       }else {
           System.out.println("结账失败，您输入的餐桌号码有误");
       }
    }

    public void mainMenu() {//管理系统主界面

        while (loop) {//一级界面
            System.out.println("===============欢迎使用餐厅管理系统===============");
            System.out.println("\t\t1 登录系统");
            System.out.println("\t\t2 退出系统");
            System.out.println("请输入你的选择(1-2):");

            key = Tools.readString(1);
            switch (key) {//判断登录用户是否合法
                case "1":
                    System.out.print("请输入用户名id：");
                    String empId = Tools.readString(10);
                    System.out.print("请输入密码：");
                    String pwd = Tools.readString(10);
                    Employee employee = employeeService.getEmployeeByIdAndPwd(empId, pwd);
                    if (employee != null) {//合法
                        System.out.println("登录成功！");
                        while (loop) {//二级界面
                            System.out.println("===============欢迎用户" + employee.getName() + "===============");
                            System.out.println("\t\t1 显示餐桌状态");
                            System.out.println("\t\t2 预定餐桌");
                            System.out.println("\t\t3 查看菜单");
                            System.out.println("\t\t4 点餐服务");
                            System.out.println("\t\t5 查看账单");
                            System.out.println("\t\t6 取消预订");
                            System.out.println("\t\t7 结账");
                            System.out.println("\t\t9 退出");
                            System.out.print("请输入你的选择(1-9):");
                            key = Tools.readString(1);
                            switch (key) {
                                case "1":
                                    diningTableStatus();
                                    break;
                                case "2":
                                    orderTable();
                                    break;
                                case "3":
                                    RestaurantManu();
                                    break;
                                case "4":
                                    orderDishes();
                                    break;
                                case "5":
                                    showBill();
                                    break;
                                case "6":
                                    cancelOrderTable();
                                    break;
                                case "7":
                                    payBill();
                                    break;
                                case "9":
                                    loop = false;
                                    break;
                                default:
                                    System.out.println("你的输入有误，请重新输入！");
                            }
                        }
                    } else {
                        System.out.println("登录失败！");
                        break;
                    }
                case "2":
                    System.out.println("退出系统");
                    loop = false;
                    break;
            }
        }
    }
}