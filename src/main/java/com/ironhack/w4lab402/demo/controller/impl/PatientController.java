package com.ironhack.w4lab402.demo.controller.impl;

import com.ironhack.w4lab402.demo.controller.interfaces.IPatientController;
import com.ironhack.w4lab402.demo.model.Employee;
import com.ironhack.w4lab402.demo.model.EmployeeStatus;
import com.ironhack.w4lab402.demo.model.Patient;
import com.ironhack.w4lab402.demo.repository.PatientRepository;
import com.ironhack.w4lab402.demo.service.impl.PatientService;
import com.ironhack.w4lab402.demo.service.interfaces.IPatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class PatientController implements IPatientController {

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    IPatientService patientService;

    @GetMapping("/patients")
    public List<Patient> getAllPatient() {
        return patientRepository.findAll();
    }


    @GetMapping("/patients/dateOfBirth")
    public List<Patient> getAllPatientByDateOfBirth(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return patientRepository.findByDateOfBirthBetween(startDate, endDate);
    }

    @GetMapping("/patients/{patientId}")
    public Patient getPatientById(@PathVariable Integer patientId) {
        return patientService.getPatientById(patientId);
    }

    @GetMapping("/patients/{department}")
    public List<Patient> getAllPatientsByDepartment(@PathVariable String department) {
        return patientRepository.findByAdmittedBy_Department(department);

    }

    @GetMapping("/patientsWithEmployeeStatusOFF")
    public List<Patient> getAllPatientsByStatus(){
        return patientRepository.findByAdmittedBy_Status(EmployeeStatus.OFF);
    }

    /* 1- Add New Patient  */
    @PostMapping("/patients")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePatient(@RequestBody @Valid Patient patient) {
        patientRepository.save(patient);
    }


    /* 5- Update patient information */
    @PutMapping("/patients/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePatient(@RequestBody @Valid Patient patient, @PathVariable Integer id) {
        patientService.updatePatient(patient, id);
    }
}