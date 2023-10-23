package com.ironhack.w4lab402.demo.controller.impl;

import com.ironhack.w4lab402.demo.controller.dto.EmployeeDepartmentDTO;
import com.ironhack.w4lab402.demo.controller.dto.EmployeeStatusDTO;
import com.ironhack.w4lab402.demo.controller.interfaces.IEmployeeController;
import com.ironhack.w4lab402.demo.model.Employee;
import com.ironhack.w4lab402.demo.model.EmployeeStatus;
import com.ironhack.w4lab402.demo.model.Patient;
import com.ironhack.w4lab402.demo.repository.EmployeeRepository;
import com.ironhack.w4lab402.demo.service.impl.EmployeeService;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController implements IEmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }



    @GetMapping("/employees/{employeeId}")
    public Employee getEmployeeById(@PathVariable Integer employeeId) {
//        Optional<Employee > employeeOptional = employeeRepository.findById(employeeId);
//        if (employeeOptional.isEmpty()) return null;
//        System.out.println(employeeOptional);
//        return employeeOptional.get();
        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Doctor not found"));
    }

    @GetMapping("/employees/status")
    public List<Employee> getEmployeeByStatus(@RequestParam EmployeeStatus status){
        List<Employee> empList = employeeRepository.findEmployeeByStatus(status);
        return empList;
    }

    @GetMapping("/employees/department")
    public List<Employee> getEmployeeByDepartment(@RequestParam String department){
        List<Employee> empList = employeeRepository.findEmployeeByDepartment(department);
        return empList;
    }


    /* 2- Add New Doctor */
    @PostMapping("/employees")
    @ResponseStatus(HttpStatus.CREATED)
    public void saveEmployee(@RequestBody @Valid Employee employee) {
        employeeRepository.save(employee);
    }

    /* 3-Change Doctor Status */
    @PatchMapping("/employees/status/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeStatus(@RequestBody @Valid EmployeeStatusDTO employeeStatusDTO, @PathVariable Integer id){
        employeeService.updateEmployeeStatus(employeeStatusDTO.getStatus(), id);

    }

    /* 4- Update Doctor’s Department */
    @PatchMapping("/employees/department/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateEmployeeDepartment(@RequestBody @Valid EmployeeDepartmentDTO employeeDepartmentDTO, @PathVariable Integer id){
        employeeService.updateEmployeeDepartment(employeeDepartmentDTO.getDepartment(), id);

    }


}
