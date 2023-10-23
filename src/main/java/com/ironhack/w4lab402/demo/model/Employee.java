package com.ironhack.w4lab402.demo.model;

import jakarta.persistence.*;

@Entity
public class Employee {

    @Id
    private Integer id;
    private String department;
    private String name;
    @Enumerated(EnumType.STRING)
    private EmployeeStatus status;


    public Employee() {
    }

    public Employee(Integer id, String department, String name, EmployeeStatus status) {
        this.department = department;
        this.name = name;
        this.status = status;
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EmployeeStatus getStatus() {
        return status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", department='" + department + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
