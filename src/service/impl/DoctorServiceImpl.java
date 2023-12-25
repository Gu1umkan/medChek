package service.impl;

import dao.impl.DoctorDaoImpl;
import model.Department;
import model.Doctor;
import model.Hospital;
import myException.NotFoundException;
import service.DoctorService;
import service.GenericService;

import java.util.ArrayList;
import java.util.List;

public class DoctorServiceImpl implements GenericService<Doctor>, DoctorService {
    private final DoctorDaoImpl doctorDao;

    public DoctorServiceImpl(DoctorDaoImpl doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        try {
            for (Doctor doctor : doctorDao.getAll()) {
                if (doctor.getId().equals(id)) {
                    return doctor;
                }
            }
            throw new NotFoundException("doctor not found");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        int count = 0;
        for (Hospital hospital : doctorDao.getAllHospital()) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(departmentId)) {
                    List<Long> doctorIDOfHospital = new ArrayList<>();
                    hospital.getDoctors().forEach(doctor -> doctorIDOfHospital.add(doctor.getId()));
                    for (Long l : doctorsId) {
                        if (doctorIDOfHospital.contains(l)) {
                            count++;
                            System.out.println("This ID: "+l+"   found ");
                        }
                        else System.out.println("This ID: "+l+"  not found ");
                    }
                    if (count == doctorsId.size()) {
                        for (Doctor doctor : hospital.getDoctors()) {
                            if (doctorsId.contains(doctor.getId()))
                                department.getDoctors().add(doctor);
                            hospital.getDoctors().remove(doctor);
                        }
                        return "Successfully assign";
                    } return "Don't successfully assign";

                }

            }
        } return "Not found Department ID!!!";
    }


    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        try {
            return doctorDao.getallByHospitalId(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        try {
            for (Hospital hospital : doctorDao.getAllHospital()) {
                for (Department department : hospital.getDepartments()) {
                    if (department.getId().equals(id)) {
                        return department.getDoctors();
                    }
                }
            }
        } catch (NotFoundException e) {
            System.out.println("Not found  department id");
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        for (Hospital hospital : doctorDao.getAllHospital()) {
            if (hospital.getId().equals(hospitalId)) {
                hospital.getDoctors().add(doctor);
                return "Successfully added";
            }
        }
        return "Not found hospital id";
    }

    @Override
    public void removeById(Long id) {
        try {
            doctorDao.removeById(id);
            System.out.println("Successfully deleted");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Doctor doctor) {

        for (Doctor doctor1 : doctorDao.getAll()) {
            if (doctor1.getId().equals(id)) {
                doctor1.setFirstName(doctor.getFirstName());
                return "Successfully updated";
            }
        }
        return "Not found id";
    }
}

