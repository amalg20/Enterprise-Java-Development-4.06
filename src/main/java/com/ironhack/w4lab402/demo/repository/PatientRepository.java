package com.ironhack.w4lab402.demo.repository;

import com.ironhack.w4lab402.demo.model.EmployeeStatus;
import com.ironhack.w4lab402.demo.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    List<Patient> findByDateOfBirthBetween(Date start, Date end);
    List<Patient> findByAdmittedBy_Department(String department);
    List<Patient> findByAdmittedBy_Status(EmployeeStatus status);

}
