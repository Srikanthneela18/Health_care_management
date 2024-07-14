package com.dev.healthcare_management.dao;

import com.dev.healthcare_management.model.Appointment;
import com.dev.healthcare_management.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class AppointmentDao {
    public void scheduleAppointment(Appointment appointment) {
        String query = "INSERT INTO Appointment (patient_id, doctor_id, appointment_date, appointment_time, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, appointment.getPatientId());
            preparedStatement.setInt(2, appointment.getDoctorId());
            preparedStatement.setString(3, appointment.getAppointmentDate());
            preparedStatement.setString(4, appointment.getAppointmentTime());
            preparedStatement.setString(5, appointment.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Appointment getAppointmentById(int appointmentId) {
        String query = "SELECT * FROM Appointment WHERE appointment_id = ?";
        Appointment appointment = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, appointmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointment_id"));
                appointment.setAppointmentId(resultSet.getInt("patient_id"));
                appointment.setDoctorId(resultSet.getInt("doctor_id"));
                appointment.setAppointmentDate(resultSet.getString("appointment_date"));
                appointment.setAppointmentTime(resultSet.getString("appointment_time"));
                appointment.setStatus(resultSet.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return appointment;
    }

    public void updateAppointment(Appointment appointment) {
        String sql = "UPDATE Appointment SET patient_id = ?, doctor_id = ?, appointment_date = ?, appointment_time = ?, status = ? WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, appointment.getPatientId());
            pstmt.setInt(2, appointment.getDoctorId());
            pstmt.setDate(3, java.sql.Date.valueOf(appointment.getAppointmentDate()));
            pstmt.setTime(4, java.sql.Time.valueOf(appointment.getAppointmentTime()));
            pstmt.setString(5, appointment.getStatus());
            pstmt.setInt(6, appointment.getAppointmentId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void cancelAppointment(int appointmentId) {
        String sql = "DELETE FROM Appointment WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, appointmentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
