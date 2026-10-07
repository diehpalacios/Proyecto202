package com.proyectu1;

import com.proyectu1.dao.DoctorDaoImpl;
import com.proyectu1.dao.PatientDaoImpl;
import com.proyectu1.idao.DoctorDao;
import com.proyectu1.idao.PatientDao;
import com.proyectu1.irepositories.DoctorRepository;
import com.proyectu1.irepositories.PatientRepository;
import com.proyectu1.models.Doctor;
import com.proyectu1.models.Patient;
import com.proyectu1.repositories.DoctorRepositoryImpl;
import com.proyectu1.repositories.PatientRepositoryImpl;




public class App {
    public static void main(String[] args) {
        DoctorDao doctorDao = new DoctorDaoImpl();
        PatientDao patientDao = new PatientDaoImpl();

        // Insertar Doctor
        Doctor doc = new Doctor(0, "Miren", "Gomez", "12345678B", 3500.0, "Cardiologia");
        int docId = doctorDao.add(doc);
        System.out.println("Doctor insertado con ID: " + docId);

        // Insertar Paciente
        Patient pat = new Patient(0, "Ion", "Alemida", "87654321C", 45, "600112233", "Arritmia");
        int patId = patientDao.add(pat, docId);
        System.out.println("Paciente insertado con ID: " + patId);

        // Test Repositorio de Paciente
        PatientRepository patientRepo = new PatientRepositoryImpl();
        Patient p = patientRepo.getPatient(patId);
        System.out.println("Paciente con su Doctor asignado: " + p);

        // Test Tarea 1
        DoctorRepository doctorRepo = new DoctorRepositoryImpl();
        Doctor d = doctorRepo.getDoctor(docId);
        System.out.println("Doctor: " + d.getName() + " | Pacientes atndidos: " + d.getAttendedPatients().size());

        // Test Tarea 2
        boolean esAtendido = patientRepo.isPatientAttendedByDoctor(patId, docId);
        System.out.println("¿El paciente " + patId + " es atendido por el doctor " + docId + "? " + esAtendido);


    }
}
