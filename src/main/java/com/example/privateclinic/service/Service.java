package com.example.privateclinic.service;

import com.example.privateclinic.domain.Consultation;
import com.example.privateclinic.domain.Medic;
import com.example.privateclinic.domain.Department;
import com.example.privateclinic.repository.ConsultationsDbRepository;
import com.example.privateclinic.repository.MedicsDbRepository;
import com.example.privateclinic.repository.DepartmentDbRepository;
import com.example.privateclinic.utils.Observable;

import java.util.Date;
import java.util.List;

public class Service extends Observable {
    private DepartmentDbRepository departmentDbRepository;
    private MedicsDbRepository medicsDbRepository;
    private ConsultationsDbRepository consultationsDbRepository;

    public Service(DepartmentDbRepository departmentDbRepository, MedicsDbRepository medicsDbRepository, ConsultationsDbRepository consultationsDbRepository) {
        this.departmentDbRepository = departmentDbRepository;
        this.medicsDbRepository = medicsDbRepository;
        this.consultationsDbRepository = consultationsDbRepository;
    }

    public List<Department> findAllDepartments() {
        return departmentDbRepository.findAll();
    }

    public Department findDepartmentById(Long id) {
        return departmentDbRepository.findOne(id);
    }

    public List<Medic> findAllMedics() {
        return medicsDbRepository.findAll();
    }

    public List<Consultation> findConsultationsForMedic(Long id) {
        return consultationsDbRepository.findAllForMed(id);
    }

    public List<Medic> getMedicsForDepartment(Long idDepartment) {
        return medicsDbRepository.getMedicForDepartment(idDepartment);
    }

    public boolean addAppointment(Long idMedic, Long cnp, String name, Date date, int time) {
        Consultation consultation = new Consultation(idMedic, cnp, name, date, time);
        return consultationsDbRepository.save(consultation);
    }
}
