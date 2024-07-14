package com.dev.healthcare_management.dao;

import com.dev.healthcare_management.model.Patient;
import com.dev.healthcare_management.util.DBConnection;

import java.sql.*;

public class PatientDao {
    public void registerPatient(Patient patient) {
        String query = "INSERT INTO Patient (name, date_of_birth, gender, address) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getDateOfBirth());
            preparedStatement.setString(3, patient.getGender());
            preparedStatement.setString(4, patient.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Patient getPatientById(int patientId) {
        String query = "SELECT * FROM Patient WHERE patient_id = ?";
        Patient patient = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, patientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                patient = new Patient();
                patient.setPatientId(resultSet.getInt("patient_id"));
                patient.setName(resultSet.getString("name"));
                patient.setDateOfBirth(resultSet.getString("date_of_birth"));
                patient.setGender(resultSet.getString("gender"));
                patient.setAddress(resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE Patient SET name=?, date_of_birth=?, gender=?, address=? WHERE patient_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patient.getName());
            stmt.setDate(2, Date.valueOf(patient.getDateOfBirth()));
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getAddress());
            stmt.setInt(5, patient.getPatientId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePatient(int patientId) {
        String sql = "DELETE FROM Patient WHERE patient_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, patientId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
