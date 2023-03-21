package com.example.privateclinic.repository;

import com.example.privateclinic.domain.Medic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicsDbRepository {
    private final String urlDb;
    private final String usernameDb;
    private final String passwordDb;

    public MedicsDbRepository(String urlDb, String usernameDb, String passwordDb) {
        this.urlDb = urlDb;
        this.usernameDb = usernameDb;
        this.passwordDb = passwordDb;
    }

    public List<Medic> getMedicForDepartment(Long idDepartment) {
        List<Medic> medici = new ArrayList<>();
        String sql = "SELECT * FROM medici WHERE id_sectie = ?";
        try (   Connection connection = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
                PreparedStatement preparedStatement = connection.prepareStatement(sql); ) {
            preparedStatement.setLong(1, idDepartment);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long idDepartmentRes = resultSet.getLong("id_sectie");
                String name = resultSet.getString("nume");
                int experience = resultSet.getInt("vechime");
                boolean resident = resultSet.getBoolean("rezident");

                Medic medic = new Medic(idDepartmentRes, name,experience, resident);
                medic.setId(id);
                medici.add(medic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medici;
    }

    public List<Medic> findAll() {
        List<Medic> medici = new ArrayList<>();
        String sql = "SELECT * FROM medici";
        try (   Connection connection = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
                PreparedStatement preparedStatement = connection.prepareStatement(sql); ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long idDepartment = resultSet.getLong("id_sectie");
                String name = resultSet.getString("nume");
                int experience = resultSet.getInt("vechime");
                boolean resident = resultSet.getBoolean("rezident");

                Medic medic = new Medic(idDepartment, name,experience, resident);
                medic.setId(id);
                medici.add(medic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medici;
    }
}
