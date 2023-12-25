package service;

import model.Department;

import java.util.List;

public interface DepartmentService extends GenericService<Department> {
    List<Department> getAllDepartmentByHospitalId(Long id);
    Department findDepartmentByName(String name);
}
