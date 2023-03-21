package com.example.privateclinic;

import com.example.privateclinic.gui.DepartmentController;
import com.example.privateclinic.repository.ConsultationsDbRepository;
import com.example.privateclinic.repository.MedicsDbRepository;
import com.example.privateclinic.repository.DepartmentDbRepository;
import com.example.privateclinic.service.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private static String[] sysArgs;
    private Service service;

    @Override
    public void start(Stage stage) throws IOException {
        String urlDb = "jdbc:postgresql://localhost:5432/Simulare5";
        String usernameDb = "postgres";
        String passDb = "1234";
        DepartmentDbRepository departmentDbRepository = new DepartmentDbRepository(urlDb, usernameDb, passDb);
        MedicsDbRepository medicsDbRepository = new MedicsDbRepository(urlDb, usernameDb, passDb);
        ConsultationsDbRepository consultationsDbRepository = new ConsultationsDbRepository(urlDb, usernameDb, passDb);
        service = new Service(departmentDbRepository, medicsDbRepository, consultationsDbRepository);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("departments-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        DepartmentController departmentController = fxmlLoader.getController();
        departmentController.setService(service);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        sysArgs = args;
        launch();
    }
}