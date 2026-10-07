package com.proyectu1.repositories;

import com.proyectu1.dao.DoctorDaoImpl;
import com.proyectu1.dao.PatientDaoImpl;
import com.proyectu1.idao.DoctorDao;
import com.proyectu1.idao.PatientDao;
import com.proyectu1.irepositories.PatientRepository;
import com.proyectu1.models.Doctor;
import com.proyectu1.models.Patient;

public class PatientRepositoryImpl implements PatientRepository {
    private PatientDao patientDao = new PatientDaoImpl();
    private DoctorDao doctorDao = new DoctorDaoImpl();

    @Override
    public Patient getPatient(int id) {
        Patient patient = patientDao.getPatient(id);
        if(patient != null){
            Doctor doctor = doctorDao.getDoctorByPatientId(patient.getId());
            patient.setDoctor(doctor);
        }
        return patient;
    }

    // Tarea 2
    @Override
    public boolean isPatientAttendedByDoctor(int patient_id, int doctor_id) {
        Patient patient = patientDao.getPatient(patient_id);
        if(patient != null){
            Doctor doctor = doctorDao.getDoctorByPatientId(patient.getId());
            return doctor != null && doctor.getId() == doctor_id;
        }

        return false;
    }
}
