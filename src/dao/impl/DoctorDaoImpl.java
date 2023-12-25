package dao.impl;

import dao.GenericDao;
import db.Database;
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
    public String add(Long id,Doctor doctor) {
        for (Hospital hospital : database.getHospitals()) {
            if(hospital.getId().equals(id)){
                hospital.getDoctors().add(doctor);
                return doctor+ "\n Succcessfully added";
            }
        }

       throw new NotFoundException("Not found id!");
    }

    @Override
    public boolean removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            return hospital.getDoctors().removeIf(doctor -> doctor.getId().equals(id));
        }
        throw  new NotFoundException("Not found id!!!");

    }

    @Override
    public List<Doctor> getallByHospitalId(Long id) {
        for (Hospital hospital : database.getHospitals()) {
           if (hospital.getId().equals(id)) return hospital.getDoctors();
        } throw  new NotFoundException("Not found id!!!");
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();
        database.getHospitals().forEach(hospital -> doctors.addAll(hospital.getDoctors()));
        return doctors;
    }

    public List<Hospital> getAllHospital(){
        return database.getHospitals();
    }

}
