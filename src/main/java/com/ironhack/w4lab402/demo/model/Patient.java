package com.ironhack.w4lab402.demo.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Patient {

    @Id

    private Integer id;
    private String name;
    private Date dateOfBirth;
    @ManyToOne
    @JoinColumn(name= "admitted_by")
    private Employee admittedBy;

    public Patient() {
    }

    public Patient(Integer id, String name, Date dateOfBirth, Employee admittedBy) {
        this.id = id;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.admittedBy = admittedBy;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Employee getAdmittedBy() {
        return admittedBy;
    }

    public void setAdmittedBy(Employee admittedBy) {
        this.admittedBy = admittedBy;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", admittedBy=" + admittedBy +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id) && Objects.equals(name, patient.name) && Objects.equals(dateOfBirth, patient.dateOfBirth) && Objects.equals(admittedBy, patient.admittedBy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, admittedBy);
    }
}
