package com.proyectu1.dao;

import com.proyectu1.idao.DoctorDao;
import com.proyectu1.models.Doctor;
import com.proyectu1.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public int add(Doctor doctor) {
        String query = "INSERT INTO doctors (name, lastname, dni, salary, speciality) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getLastname());
            ps.setString(3, doctor.getDni());
            ps.setDouble(4, doctor.getSalary());
            ps.setString(5, doctor.getSpeciality());
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
        String query = "DELETE FROM doctors WHERE id = ?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Doctor getDoctor(int id) {
        String query = "SELECT * FROM doctors WHERE id = ?";
        Doctor doctor = null;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                doctor = new Doctor(
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("lastname"), rs.getString("dni"),
                        rs.getDouble("salary"), rs.getString("speciality")

                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }

    @Override
    public List<Doctor> getDoctors() {
        List<Doctor> list = new ArrayList<>();
        String query = "SELECT * FROM doctors";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                list.add(new Doctor(
                        rs.getInt("id"), rs.getString("name"),
                        rs.getString("lastname"), rs.getString("dni"),
                        rs.getDouble("salary"), rs.getString("speciality")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean update(Doctor doctor) {
        String query = "UPDATE doctors SET name=?, lastname=?, dni=?, salary=?, speciality=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, doctor.getName());
            ps.setString(2, doctor.getLastname());
            ps.setString(3, doctor.getDni());
            ps.setDouble(4, doctor.getSalary());
            ps.setString(5, doctor.getSpeciality());
            ps.setInt(6, doctor.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Doctor getDoctorByPatientId(int patient_id) {
        String query = "SELECT d.* FROM doctors d INNER JOIN patients p ON d.id = p.doctor_id WHERE p.id = ?";
        Doctor doctor = null;
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, patient_id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    doctor = new Doctor(
                            rs.getInt("id"), rs.getString("name"),
                            rs.getString("lastname"), rs.getString("dni"),
                            rs.getDouble("salary"), rs.getString("speciality")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return doctor;
    }
}
