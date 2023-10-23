package com.ironhack.w4lab402.demo.controller.dto;

import com.ironhack.w4lab402.demo.model.EmployeeStatus;

public class EmployeeStatusDTO {

    private EmployeeStatus status;

    public EmployeeStatusDTO() {
    }

    public EmployeeStatusDTO(EmployeeStatus status) {
        this.status = status;
    }

    public void setStatus(EmployeeStatus status) {
        this.status = status;
    }

    public EmployeeStatus getStatus(){
        return status;
    }
}
