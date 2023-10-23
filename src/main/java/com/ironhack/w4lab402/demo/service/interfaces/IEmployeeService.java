package com.ironhack.w4lab402.demo.service.interfaces;

import com.ironhack.w4lab402.demo.controller.dto.EmployeeStatusDTO;
import com.ironhack.w4lab402.demo.model.EmployeeStatus;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IEmployeeService {

    public void updateEmployeeStatus(EmployeeStatus employeeStatus, Integer id);
    public void updateEmployeeDepartment(String department, Integer id);
}
