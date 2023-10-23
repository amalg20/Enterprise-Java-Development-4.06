package com.ironhack.w4lab402.demo.service.interfaces;

import com.ironhack.w4lab402.demo.model.Patient;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPatientService {

    public Patient getPatientById(Integer patientId);
    public void updatePatient( Patient patient,  Integer id);

}
