package dao.impl;

import dao.HospitalDao;
import db.Database;
import idGenerator.IDGenerator;
import model.Hospital;
import myException.NotFoundException;

import java.util.List;

public class HospitalDaoImpl implements HospitalDao {
    private final Database database;

    public HospitalDaoImpl(Database database) {
        this.database = database;
    }


    @Override
    public String add(Hospital hospital) {
        hospital.setId(IDGenerator.idHospital());
        database.getHospitals().add(hospital);
        return hospital + "\nSuccrssfully added";
    }

    @Override
    public boolean remove(Hospital hospital) {
        return database.getHospitals().remove(hospital);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return database.getHospitals();
    }




}
