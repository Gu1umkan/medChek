package service.impl;

import dao.impl.DepartmentDaoImpl;
import model.Department;
import model.Hospital;
import myException.NotFoundException;
import service.DepartmentService;
import service.GenericService;

import java.util.List;

public class DepartmentServiceImpl implements GenericService<Department>, DepartmentService {
    private final DepartmentDaoImpl departmentDao;

    public DepartmentServiceImpl(DepartmentDaoImpl departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAllDepartmentByHospitalId(Long id) {
        try {
            return departmentDao.getallByHospitalId(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            for (Department department : departmentDao.getAll()) {
                if (department.getDepartmentName().equalsIgnoreCase(name)) {
                    return department;
                }
            } throw new NotFoundException("Department name not found");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        try {
            return departmentDao.add(hospitalId, department);
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public void removeById(Long id) {
        try {
            departmentDao.removeById(id);
            System.out.println("deleted");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Department department) {
        for (Department department1 : departmentDao.getAll()) {
            if (department1.getId().equals(id)) {
                department1.setDepartmentName(department.getDepartmentName());
                return "Successfully updated";
            }
        }
        return "Not found id";
    }
}
