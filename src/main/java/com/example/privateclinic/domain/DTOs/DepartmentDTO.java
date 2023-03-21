package com.example.privateclinic.domain.DTOs;

import com.example.privateclinic.domain.Department;

public class DepartmentDTO {
    private Long id;
    private String name;
    private String price;
    private String duration;

    public DepartmentDTO(Department department) {
        id = department.getId();
        name = department.getName();
        price = String.valueOf(department.getConsultationPrice());
        duration = String.valueOf(department.getDuration());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
