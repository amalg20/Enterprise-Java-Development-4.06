package com.ironhack.w4lab402.demo.service.impl;

import com.ironhack.w4lab402.demo.model.Patient;
import com.ironhack.w4lab402.demo.repository.PatientRepository;
import com.ironhack.w4lab402.demo.service.interfaces.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
public class PatientService implements IPatientService {

    @Autowired
    PatientRepository patientRepository;


    public Patient getPatientById(Integer patientId) {
        Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if (patientOptional.isEmpty()) return null;
        return patientOptional.get();
    }

    @Override
    public void updatePatient(Patient patient, Integer id) {

        Optional<Patient> patientOptional = patientRepository.findById(id);
        if (patientOptional.isEmpty()) return;
        Patient oldPatient = patientOptional.get();
        if(oldPatient.equals(patient)) return;
        //Imagine that Id = 7.
        /*
        THINGS WE HAVE TO KNOW
         1º A Repo can not have repeated IDs.
         2º I can rewrite the ID object

        If I rewrite the ID of and object, I can put this new object in the place of the older one.

         */
        patient.setId(oldPatient.getId());
        patientRepository.save(patient);
    }


}
