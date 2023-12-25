package dao.impl;

import dao.HospitalDao;
import db.Database;
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
        database.getHospitals().add(hospital);
        return hospital + "\nSuccrssfully added";
    }

    @Override
    public boolean removeById(Long id) {
        return database.getHospitals().removeIf(hospital -> hospital.getId().equals(id));
    }

    @Override
    public List<Hospital> getAll() {
        return database.getHospitals();
    }

    @Override
    public Hospital getHospitalById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id)) ;
            return hospital;
        }
        throw new NotFoundException("Not found hospital id");
    }


}
