package dao.impl;

import dao.GenericDao;
import db.Database;
import idGenerator.IDGenerator;
import model.Doctor;
import model.Hospital;
import model.Patient;
import myException.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements GenericDao<Patient> {
    private final Database database;

    public PatientDaoImpl(Database database) {
        this.database = database;
    }


    @Override
    public String add(Hospital hospital, Patient patient) {
        patient.setId(IDGenerator.idPatient());
        hospital.getPatients().add(patient);
        return patient + "\nSuccessfully added";
    }

    @Override
    public boolean remove(Hospital hospital, Patient patient) {
        return hospital.getPatients().remove(patient);

    }


    @Override
    public List<Hospital> getAllHospital() {
        return database.getHospitals();
    }
}

