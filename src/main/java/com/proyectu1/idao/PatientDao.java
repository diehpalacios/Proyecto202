package com.proyectu1.idao;

import com.proyectu1.models.Patient;

import java.util.List;

public interface PatientDao {
    int add(Patient patient, int doctorId);

    void delete(int id);

    Patient getPatient(int id);

    List<Patient> getPatients();

    boolean update(Patient patient);

    List<Patient> getPatientsByDoctorId(int doctor_id);

}
