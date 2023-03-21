package com.example.privateclinic.domain.DTOs;

import com.example.privateclinic.domain.Consultation;

public class ConsultationDTO {
    private Long id;
    private String department;
    private String patientName;
    private String dateTime;

    public ConsultationDTO(Consultation consultation, String department) {
        id = consultation.getId();
        this.department = department;
        patientName = consultation.getPatientName();
        dateTime = consultation.getDate().toString() + " " + consultation.getTime();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
