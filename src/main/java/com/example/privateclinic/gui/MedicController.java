package com.example.privateclinic.gui;

import com.example.privateclinic.domain.DTOs.ConsultationDTO;
import com.example.privateclinic.domain.Medic;
import com.example.privateclinic.service.Service;
import com.example.privateclinic.utils.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.stream.Collectors;

public class MedicController implements Observer {
    ObservableList<ConsultationDTO> consultations = FXCollections.observableArrayList();

    public Label nameLabel;

    public TableView<ConsultationDTO> appointmentsTable;
    public TableColumn<ConsultationDTO, String> departmentCol;
    public TableColumn<ConsultationDTO, String> dateCol;
    public TableColumn<ConsultationDTO, String> nameCol;

    private Service service;
    private Medic medic;

    public void setAttr(Service service, Medic medic) {
        this.service = service;
        this.medic = medic;
        nameLabel.setText("Dr. " + this.medic.getName());
        this.service.addObserver(this);
        refreshData();
    }

    private void refreshData() {
        consultations.setAll(this.service.findConsultationsForMedic(this.medic.getId())
                .stream()
                .map(x -> new ConsultationDTO(x, this.service.findDepartmentById(medic.getIdDepartment()).getName()))
                .collect(Collectors.toList()));
    }

    @FXML
    private void initialize() {
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("department"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        appointmentsTable.setItems(consultations);
    }

    @Override
    public void update() {
        refreshData();
    }
}
