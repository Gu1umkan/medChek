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


//        Hospital hospital1 = database.getHospitals().stream().filter(hospital -> hospital.getId().equals(id)).
//                findFirst().orElseThrow(() -> new NotFoundException("Not found id!!!!"));
//       hospital1.getDepartments().add(department);
//       return department + "\nSuccesfully added";
//        for (Hospital hospital : database.getHospitals()) {
//            if (hospital.getId().equals(id)) {
//                hospital.getDepartments().add(department);
//                return department + "\nSuccesfully added";
//            }
//        }
//        throw new NotFoundException("Not found id!!!");
    }

    @Override
    public boolean remove(Hospital hospital,Department department) {
           return hospital.getDepartments().remove(department);

//       database.getHospitals().stream().flatMap(hospital -> hospital.getDepartments().stream().
//                filter(department -> department.getId().equals(id))).findFirst().orElseThrow(
//                        ()->new NotFoundException("Not found id")).;
//        for (Hospital hospital : database.getHospitals()) {
//            return hospital.getDepartments().removeIf(x -> x.getId().equals(id));
//        }

    }

//    @Override
//    public List<Department> getallByHospital(Hospital hospital) {
////        for (Hospital hospital : database.getHospitals()) {
////            if (hospital.getId().equals(id))
//           return hospital.getDepartments();
//        }
//   //     throw new NotFoundException("Not found id!!!");



    @Override
    public List<Hospital> getAllHospital() {
      return database.getHospitals();
    }

}
