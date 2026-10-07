package com.proyectu1.dao;

import com.proyectu1.idao.PatientDao;
import com.proyectu1.models.Patient;
import com.proyectu1.utils.DatabaseConnection;

import java.util.List;
import java.sql.*;
import java.util.ArrayList;

public class PatientDaoImpl implements PatientDao {

    @Override
    public int add(Patient patient, int doctorId) {
        String query = "INSERT INTO patients (name, lastname, dni, age, phone, disease, doctor_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastname());
            ps.setString(3, patient.getDni());
            ps.setInt(4, patient.getAge());
            ps.setString(5, patient.getPhone());
            ps.setString(6, patient.getDisease());
            ps.setInt(7, doctorId);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM patients WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Patient getPatient(int id) {
        String query = "SELECT * FROM patients WHERE id = ?";
        Patient patient = null;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    patient = new Patient(
                            rs.getInt("id"), rs.getString("name"),
                            rs.getString("lastname"), rs.getString("dni"),
                            rs.getInt("age"), rs.getString("phone"),
                            rs.getString("disease")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patient;
    }

    @Override
    public List<Patient> getPatients() {
        List<Patient> list = new ArrayList<>();
        String query = "SELECT * FROM patients";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Patient(
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("lastname"), rs.getString("dni"),
                        rs.getInt("age"), rs.getString("phone"),
                        rs.getString("disease")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean update(Patient patient) {
        String query = "UPDATE patients SET name=?, lastname=?, dni=?, age=?, phone=?, disease=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getLastname());
            ps.setString(3, patient.getDni());
            ps.setInt(4, patient.getAge());
            ps.setString(5, patient.getPhone());
            ps.setString(6, patient.getDisease());
            ps.setInt(7, patient.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Patient> getPatientsByDoctorId(int doctor_id) {
        List<Patient> list = new ArrayList<>();
        String query = "SELECT * FROM patients WHERE doctor_id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, doctor_id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(new Patient(
                            rs.getInt("id"), rs.getString("name"),
                            rs.getString("lastname"), rs.getString("dni"),
                            rs.getInt("age"), rs.getString("phone"),
                            rs.getString("disease")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
