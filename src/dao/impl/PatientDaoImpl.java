package dao.impl;

import dao.GenericDao;
import db.Database;
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
    public String add(Long hospitslId, Patient patient) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(hospitslId))
                hospital.getPatients().add(patient);
            return patient + "\nSuccessfully added";
        }
        throw new NotFoundException("Not found id");
    }

    @Override
    public boolean removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            return hospital.getPatients().removeIf(patient -> patient.getId().equals(id));
        }
        throw new NotFoundException("Not found id!!!");
    }

    @Override
    public List<Patient> getallByHospitalId(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id)) return hospital.getPatients();
        }
        throw new NotFoundException("Not found id");
    }


    @Override
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
       database.getHospitals().forEach(hospital -> patients.addAll(hospital.getPatients()));
        return patients;
    }
}

