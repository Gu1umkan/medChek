package dao.impl;

import dao.GenericDao;
import db.Database;
import idGenerator.IDGenerator;
import model.Department;
import model.Doctor;
import model.Hospital;
import myException.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class DepartmentDaoImpl implements GenericDao<Department> {
    private final Database database;

    public DepartmentDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Hospital hospital, Department department) {
        department.setId(IDGenerator.idDepartment());
       hospital.getDepartments().add(department);
        return department + "\nSuccesfully added";

    }

    @Override
    public boolean remove(Hospital hospital,Department department) {
           return hospital.getDepartments().remove(department);

    }

    @Override
    public List<Hospital> getAllHospital() {
      return database.getHospitals();
    }

}
