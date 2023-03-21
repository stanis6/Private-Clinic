package com.example.privateclinic.gui;

import com.example.privateclinic.domain.DTOs.MedicDTO;
import com.example.privateclinic.domain.Department;
import com.example.privateclinic.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;

import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

public class AppointmentController {
    public Label statusLabel;
    ObservableList<MedicDTO> medics = FXCollections.observableArrayList();

    public TextField departmentField;
    public ComboBox<MedicDTO> medicComboBox;
    public TextField cnpField;
    public TextField nameField;
    public DatePicker datePicker;
    public Spinner<Integer> timeSpinner;

    private Service service;
    private Department department;

    public void setAttr(Service service, Department department) {
        this.service = service;
        this.department = department;
        medicComboBox.setItems(medics);
        departmentField.setText(department.getName());
        departmentField.setEditable(false);
        departmentField.setDisable(true);
        refreshData();
    }

    private void refreshData() {
        medics.setAll(service.getMedicsForDepartment(department.getId())
                .stream()
                .map(MedicDTO::new)
                .collect(Collectors.toList()));
    }

    public void appointTriggered() {
        MedicDTO med = medicComboBox.getValue();
        Long medId = med.getId();
        Long cnp = Long.parseLong(cnpField.getText());
        String name = nameField.getText();
        Date data = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
        int ora = timeSpinner.getValue();
        boolean res = service.addAppointment(medId, cnp, name, data, ora);
        if (res) {
            statusLabel.setText("Consultatia a fost programata");
            service.notifyObs();
        } else {
            statusLabel.setText("Consultatia nu poate fi programata");
        }
    }
}
