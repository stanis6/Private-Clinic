package com.example.privateclinic.repository;

import com.example.privateclinic.domain.Consultation;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class ConsultationsDbRepository {
    private final String urlDb;
    private final String usernameDb;
    private final String passwordDb;

    public ConsultationsDbRepository(String urlDb, String usernameDb, String passwordDb) {
        this.urlDb = urlDb;
        this.usernameDb = usernameDb;
        this.passwordDb = passwordDb;
    }

    public List<Consultation> findAllForMed(Long idMedic) {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT * FROM consultatii WHERE id_medic = ? ORDER BY data ASC, ora ASC";
        try (   Connection connection = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
                PreparedStatement preparedStatement = connection.prepareStatement(sql); ) {
            preparedStatement.setLong(1, idMedic);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id =  resultSet.getLong("id");
                Long patientCnp = resultSet.getLong("cnp_pacient");
                String patientName = resultSet.getString("nume_pacient");
                Date date = resultSet.getDate("data");
                int time = resultSet.getInt("ora");

                Consultation consultation = new Consultation(idMedic, patientCnp, patientName, date, time);
                consultations.add(consultation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return consultations;
    }

    public Long getLowestFreeId() {
        String sql = "SELECT id FROM consultatii ORDER BY id DESC";
        try (   Connection connection = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
                PreparedStatement preparedStatement = connection.prepareStatement(sql); ) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getLong("id") + 1;
            } else {
                return 1L;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public boolean save(Consultation consultatie) {
        Date date = consultatie.getDate();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString  = formatter.format(date);
        String sql = "INSERT INTO consultatii(id, id_medic, cnp_pacient, nume_pacient, data, ora) VALUES " +
                "(?, ?, ?, ?, \'"+ dateString +"\', ?)";

        try (
                Connection connection = DriverManager.getConnection(urlDb, usernameDb, passwordDb);
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ) {
            preparedStatement.setLong(1, getLowestFreeId());
            preparedStatement.setLong(2, consultatie.getIdMedic());
            preparedStatement.setLong(3, consultatie.getPatientCnp());
            preparedStatement.setString(4, consultatie.getPatientName());
            preparedStatement.setInt(5, consultatie.getTime());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
