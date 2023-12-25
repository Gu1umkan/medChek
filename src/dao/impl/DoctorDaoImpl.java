package dao.impl;

import dao.GenericDao;
import db.Database;
import idGenerator.IDGenerator;
import model.Doctor;
import model.Hospital;
import myException.NotFoundException;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements GenericDao<Doctor> {
    private final Database database;

    public DoctorDaoImpl(Database database) {
        this.database = database;
    }


    @Override
    public String add(Hospital hospital,Doctor doctor) {
        doctor.setId(IDGenerator.idDoctor());
       hospital.getDoctors().add(doctor);
           return doctor+ " \nSuccessfully added";
    }

    @Override
    public boolean remove(Hospital hospital,Doctor doctor) {
       return hospital.getDoctors().remove(doctor);
       }
    public List<Hospital> getAllHospital(){
        return database.getHospitals();
    }

}
