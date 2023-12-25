package service.impl;

import dao.impl.DepartmentDaoImpl;
import model.Department;
import model.Hospital;
import myException.NotFoundException;
import service.DepartmentService;
import service.GenericService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentDaoImpl departmentDao;

    public DepartmentServiceImpl(DepartmentDaoImpl departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAllDepartmentByHospitalId(Long id) {
        try {
            for (Hospital hospital : departmentDao.getAllHospital()) {
                if (hospital.getId().equals(id)) return hospital.getDepartments();
            }
            throw new NotFoundException("Not found id!!!");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            for (Hospital hospital : departmentDao.getAllHospital()) {
                for (Department department : hospital.getDepartments()) {
                    if (department.getDepartmentName().equalsIgnoreCase(name)) {
                        return department;
                    }
                }
                throw new NotFoundException("Department name not found");
            }
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Department department) {
        try {
            for (Hospital hospital : departmentDao.getAllHospital()) {
                if (hospital.getId().equals(hospitalId)) {
                    return departmentDao.add(hospital, department);
                }
            }
            throw new NotFoundException("Not found id!!!");
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public void removeById(Long id) {
        int count = 0;
            for (Hospital hospital : departmentDao.getAllHospital()) {
                if (hospital.getDepartments().removeIf(x -> x.getId().equals(id))) {
                    System.out.println("Successfully deleted✅");
                    count++;
                }
            }
            if (count == 0) System.out.println("Not found id❗️");
    }

    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : departmentDao.getAllHospital()) {
            for (Department department1 : hospital.getDepartments()) {
                if (department1.getId().equals(id)) {
                    department1.setDepartmentName(department.getDepartmentName());
                    return "Successfully updated";
                }
            }
        }
        return "Not found id";
    }
}
