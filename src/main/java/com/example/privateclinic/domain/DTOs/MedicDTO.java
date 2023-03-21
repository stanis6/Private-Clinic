package com.example.privateclinic.domain.DTOs;

import com.example.privateclinic.domain.Medic;

public class MedicDTO {
    private Long id;
    private String name;

    public MedicDTO(Medic medic) {
        id = medic.getId();
        name = medic.getName();
    }

    @Override
    public String toString() {
        return name;
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
}
