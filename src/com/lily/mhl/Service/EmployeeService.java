package com.lily.mhl.Service;

import com.lily.mhl.DAO.EmployeeDAO;
import com.lily.mhl.Domain.Employee;

/**
 * @author Lily
 * @version 1.0
 */
public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public Employee getEmployeeByIdAndPwd(String name, String pwd) {
        return employeeDAO.querySingle("select * from employee where empId = ? and pwd = md5(?)"
                , Employee.class, name, pwd);
    }
}
