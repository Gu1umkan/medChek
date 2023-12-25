package dao;

import model.Department;
import model.Hospital;

import java.util.List;

public interface GenericDao<T>{
    String add (Hospital hospital, T t);
    boolean remove(Hospital hospital,T t);

    List<Hospital> getAllHospital();

}
