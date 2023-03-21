module com.example.practicalexam {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.privateclinic to javafx.fxml;
    exports com.example.privateclinic;
    exports com.example.privateclinic.domain.DTOs;
    exports com.example.privateclinic.domain.enums;
    exports com.example.privateclinic.domain;
    exports com.example.privateclinic.repository;
    exports com.example.privateclinic.service;
    exports com.example.privateclinic.utils;
    exports com.example.privateclinic.gui;
    opens com.example.privateclinic.gui to javafx.fxml;
}