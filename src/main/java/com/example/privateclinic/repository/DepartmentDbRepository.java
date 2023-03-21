package com.example.privateclinic.repository;

import com.example.privateclinic.domain.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDbRepository {
    private final String urlDb;
    private final String usernameDb;
    private final String passwordDb;

    public DepartmentDbRepository(String urlDb, String usernameDb, String passwordDb) {
        this.urlDb = urlDb;
        this.usernameDb = usernameDb;
        this.passwordDb = passwordDb;
    }

    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM sectii";
        try (   Connection connection = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
                PreparedStatement preparedStatement = connection.prepareStatement(sql); ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long idLeader = resultSet.getLong("id_sef");
                Double price = (double)resultSet.getFloat("pret");
                int duration = resultSet.getInt("durata");
                String name = resultSet.getString("nume");

                Department department = new Department(name, idLeader, price, duration);
                department.setId(id);
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    public Department findOne(Long id) {
        Department department = null;
        String sql = "SELECT * FROM sectii WHERE id = ?";
        try (   Connection connection = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
                PreparedStatement preparedStatement = connection.prepareStatement(sql); ) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Long idSef = resultSet.getLong("id_sef");
                double price = resultSet.getFloat("pret");
                int duration = resultSet.getInt("durata");
                String name = resultSet.getString("nume");

                department = new Department(name, idSef, price, duration);
                department.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return department;
    }
}
