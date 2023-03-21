package com.example.privateclinic.gui;

import com.example.privateclinic.HelloApplication;
import com.example.privateclinic.domain.DTOs.DepartmentDTO;
import com.example.privateclinic.domain.Medic;
import com.example.privateclinic.domain.Department;
import com.example.privateclinic.service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class DepartmentController {
    ObservableList<DepartmentDTO> departments = FXCollections.observableArrayList();

    public TableView<DepartmentDTO> departmentTable;
    public TableColumn<DepartmentDTO, String> departmentCol;
    public TableColumn<DepartmentDTO, String> priceCol;
    public TableColumn<DepartmentDTO, String> durationCol;

    private Service service;

    public void setService(Service service) throws IOException {
        this.service = service;
        departments.setAll(service.findAllDepartments()
                .stream()
                .map(DepartmentDTO::new)
                .collect(Collectors.toList()));
        List<Medic> medics = service.findAllMedics();
        for (Medic medic : medics) {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("medic-window.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            MedicController medicController = fxmlLoader.getController();
            medicController.setAttr(service, medic);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Programari");
            stage.show();
        }
    }

    @FXML
    private void initialize() {
        departmentCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        durationCol.setCellValueFactory(new PropertyValueFactory<>("duration"));

        departmentTable.setItems(departments);
    }

    public void sectionSelected() throws IOException {
        if (departmentTable.getSelectionModel().isEmpty()) {
            return;
        }

        DepartmentDTO departmentDTO = departmentTable.getSelectionModel().getSelectedItem();

        Department department = service.findDepartmentById(departmentDTO.getId());

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("appointment-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        AppointmentController ctr = fxmlLoader.getController();
        ctr.setAttr(service, department);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Programare");
        stage.show();
    }
}