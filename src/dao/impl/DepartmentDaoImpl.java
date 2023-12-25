package dao.impl;

import dao.GenericDao;
import db.Database;
import model.Department;
import model.Doctor;
import model.Hospital;
import myException.NotFoundException;

import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoImpl implements GenericDao<Department> {
    private final Database database;

    public DepartmentDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public String add(Long id, Department department) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id)) {
                hospital.getDepartments().add(department);
                return department + "\nSuccesfully added";
            }
        }
        throw new NotFoundException("Not found id!!!");
    }

    @Override
    public boolean removeById(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            return hospital.getDepartments().removeIf(x -> x.getId().equals(id));
        }
        throw new NotFoundException("Not found id");
    }

    @Override
    public List<Department> getallByHospitalId(Long id) {
        for (Hospital hospital : database.getHospitals()) {
            if (hospital.getId().equals(id)) return hospital.getDepartments();
        }
        throw new NotFoundException("Not found id!!!");

    }

    @Override
    public List<Department> getAll() {
        List<Department> departments = new ArrayList<>();
        database.getHospitals().forEach(hospital -> departments.addAll(hospital.getDepartments()));
        return departments;
    }

}
