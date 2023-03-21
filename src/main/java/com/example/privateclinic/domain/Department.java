package com.example.privateclinic.domain;

public class Department extends Entity<Long> {
    private String name;
    private Long idLeader;
    private double consultationPrice;
    private int duration;

    public Department(String name, Long idLeader, double consultationPrice, int duration) {
        this.name = name;
        this.idLeader = idLeader;
        this.consultationPrice = consultationPrice;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdLeader() {
        return idLeader;
    }

    public void setIdLeader(Long idLeader) {
        this.idLeader = idLeader;
    }

    public double getConsultationPrice() {
        return consultationPrice;
    }

    public void setConsultationPrice(double consultationPrice) {
        this.consultationPrice = consultationPrice;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
