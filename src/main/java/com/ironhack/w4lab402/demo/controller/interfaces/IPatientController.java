package com.ironhack.w4lab402.demo.controller.interfaces;


import com.ironhack.w4lab402.demo.model.Patient;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

public interface IPatientController {

    public List<Patient> getAllPatient();
    public Patient getPatientById(Integer patientId);
    public List<Patient> getAllPatientByDateOfBirth(Date startDate, Date endDate);

    public List<Patient> getAllPatientsByDepartment(String department);
    public List<Patient> getAllPatientsByStatus();
}
