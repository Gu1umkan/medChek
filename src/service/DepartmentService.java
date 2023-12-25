package service;

import model.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getAllDepartmentByHospitalId(Long id);
    Department findDepartmentByName(String name);
}
