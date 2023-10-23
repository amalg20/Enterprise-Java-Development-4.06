package com.ironhack.w4lab402.demo.controller.interfaces;

import com.ironhack.w4lab402.demo.model.Employee;
import com.ironhack.w4lab402.demo.model.EmployeeStatus;

import java.util.List;

public interface IEmployeeController {

    public List<Employee> getAllEmployee();
    public Employee getEmployeeById(Integer employeeId);
    public List<Employee> getEmployeeByStatus(EmployeeStatus status);
    public List<Employee> getEmployeeByDepartment(String department);
}
