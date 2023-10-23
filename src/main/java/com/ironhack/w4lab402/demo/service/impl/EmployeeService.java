package com.ironhack.w4lab402.demo.service.impl;

import com.ironhack.w4lab402.demo.controller.dto.EmployeeStatusDTO;
import com.ironhack.w4lab402.demo.model.Employee;
import com.ironhack.w4lab402.demo.model.EmployeeStatus;
import com.ironhack.w4lab402.demo.repository.EmployeeRepository;
import com.ironhack.w4lab402.demo.service.interfaces.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService  implements IEmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public void updateEmployeeStatus(EmployeeStatus employeeStatus, Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) return;
        Employee employee = employeeOptional.get();
        employee.setStatus(employeeStatus);
        employeeRepository.save(employee);
    }

    @Override
    public void updateEmployeeDepartment(String department, Integer id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isEmpty()) return;
        Employee employee = employeeOptional.get();
        employee.setDepartment(department);
        employeeRepository.save(employee);
    }


}
