package dao;

import model.Hospital;

import java.util.List;

public interface HospitalDao {
    String add(Hospital hospital);
    boolean remove(Hospital hospital);
    List<Hospital> getAllHospital();
}
