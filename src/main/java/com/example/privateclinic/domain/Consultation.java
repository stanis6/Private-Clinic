package com.example.privateclinic.domain;

import java.util.Date;

public class Consultation extends Entity<Long> {
    private Long idMedic;
    private Long patientCnp;
    private String patientName;
    private Date date;
    private int time;

    public Consultation(Long idMedic, Long patientCnp, String patientName, Date date, int time) {
        this.idMedic = idMedic;
        this.patientCnp = patientCnp;
        this.patientName = patientName;
        this.date = date;
        this.time = time;
    }

    public Long getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(Long idMedic) {
        this.idMedic = idMedic;
    }

    public Long getPatientCnp() {
        return patientCnp;
    }

    public void setPatientCnp(Long patientCnp) {
        this.patientCnp = patientCnp;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
