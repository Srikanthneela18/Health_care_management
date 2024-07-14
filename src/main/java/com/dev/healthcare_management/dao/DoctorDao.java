package com.dev.healthcare_management.dao;

import com.dev.healthcare_management.model.Doctor;
import com.dev.healthcare_management.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DoctorDao {
    public void addDoctor(Doctor doctor) {
        String query = "INSERT INTO Doctor (name, specialization, contact_number, email) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getSpecialization());
            preparedStatement.setString(3, doctor.getContactNumber());
            preparedStatement.setString(4, doctor.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Doctor getDoctorById(int doctorId) {
        String query = "SELECT * FROM Doctor WHERE doctor_id = ?";
        Doctor doctor = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setName(resultSet.getString("name"));
                doctor.setSpecialization(resultSet.getString("specialization"));
                doctor.setContactNumber(resultSet.getString("contact_number"));
                doctor.setEmail(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    public  void updateDoctor(Doctor doctor) {
        String sql = "UPDATE Doctor SET name=?, specialization=?, contact_number=?, email=? WHERE doctor_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setString(3, doctor.getContactNumber());
            stmt.setString(4, doctor.getEmail());
            stmt.setInt(5, doctor.getDoctorId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDoctor(int doctorId) {
        String sql = "DELETE FROM Doctor WHERE doctor_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctorId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
