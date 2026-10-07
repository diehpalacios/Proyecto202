package com.proyectu1.irepositories;

import com.proyectu1.models.Patient;

public interface PatientRepository {
    Patient getPatient(int id);
    boolean isPatientAttendedByDoctor(int patient_id, int doctor_id); // Tarea 2
}
