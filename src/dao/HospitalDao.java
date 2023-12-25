package dao;

import model.Hospital;

import java.util.List;

public interface HospitalDao {
    String add(Hospital hospital);
    boolean removeById(Long id);
    List<Hospital> getAll();
    Hospital getHospitalById(Long id);
}
