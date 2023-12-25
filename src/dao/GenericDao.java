package dao;

import model.Department;

import java.util.List;

public interface GenericDao<T>{
    String add (Long id,T t);
    boolean removeById(Long id);
    List<T> getallByHospitalId(Long id);

    List<T> getAll();

}
