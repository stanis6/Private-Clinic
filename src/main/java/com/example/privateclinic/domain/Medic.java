package com.example.privateclinic.domain;

public class Medic extends Entity<Long> {
    private Long idDepartment;
    private String name;
    private int experience;
    private boolean resident;

    public Medic(Long idDepartment, String name, int experience, boolean resident) {
        this.idDepartment = idDepartment;
        this.name = name;
        this.experience = experience;
        this.resident = resident;
    }

    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public boolean isResident() {
        return resident;
    }

    public void setResident(boolean resident) {
        this.resident = resident;
    }
}
