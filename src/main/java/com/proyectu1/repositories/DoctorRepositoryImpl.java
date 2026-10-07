package com.proyectu1.repositories;

import com.proyectu1.dao.DoctorDaoImpl;
import com.proyectu1.dao.PatientDaoImpl;
import com.proyectu1.idao.DoctorDao;
import com.proyectu1.idao.PatientDao;
import com.proyectu1.irepositories.DoctorRepository;
import com.proyectu1.models.Doctor;
import com.proyectu1.models.Patient;

import java.util.List;

public class DoctorRepositoryImpl implements DoctorRepository {
    private DoctorDao doctorDao = new DoctorDaoImpl();
    private PatientDao patientDao = new PatientDaoImpl();

    @Override
    public Doctor getDoctor(int doctor_id) {
        Doctor doctor = doctorDao.getDoctor(doctor_id);
        if (doctor != null) {
            List<Patient> patients = patientDao.getPatientsByDoctorId(doctor_id);
            doctor.setAttendedPatients(patients);
        }
        return doctor;
    }
}
