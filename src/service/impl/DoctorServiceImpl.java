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

public class DoctorServiceImpl implements DoctorService {
    private final DoctorDaoImpl doctorDao;

    public DoctorServiceImpl(DoctorDaoImpl doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public Doctor findDoctorById(Long id) {
        try {
            for (Hospital hospital : doctorDao.getAllHospital()) {
                for (Doctor doctor : hospital.getDoctors()) {
                    if (doctor.getId().equals(id)) {
                        return doctor;
                    }
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
                            System.out.println("This ID: " + l + "   found ");
                        } else System.out.println("This ID: " + l + "  not found ");
                    }
                    if (count == doctorsId.size()) {
                        List<Doctor> doctorsToRemove = new ArrayList<>();
                        for (Doctor doctor : hospital.getDoctors()) {
                            if (doctorsId.contains(doctor.getId())) {
                                department.getDoctors().add(doctor);
                                doctorsToRemove.add(doctor);
                            }
                        }
                        hospital.getDoctors().removeAll(doctorsToRemove);
                        return "Successfully assign";
                    } else return "Don't successfully assign";
                }
            }
        }
        return "Not found Department ID!!!";
    }


    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        List<Doctor> doctors = new ArrayList<>();
        try {
            for (Hospital hospital : doctorDao.getAllHospital()) {
                if (hospital.getId().equals(id)) {
                    hospital.getDepartments().forEach(department -> doctors.addAll(department.getDoctors()));
                    if (!doctors.containsAll(hospital.getDoctors())) {
                        doctors.addAll(hospital.getDoctors());
                    } return doctors;
                }
            }
            throw new NotFoundException("Not found id!!!");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return doctors;
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
            throw new NotFoundException("Not found department id!!!");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        for (Hospital hospital : doctorDao.getAllHospital()) {
            if (hospital.getId().equals(hospitalId)) {
                return doctorDao.add(hospital, doctor);
            }
        }
        return "Not found hospital id";
    }

    @Override
    public void removeById(Long id) {
        boolean remove = false;
        boolean remove1 = false;
        for (Hospital hospital : doctorDao.getAllHospital()) {
            if (hospital.getDoctors().removeIf(doctor -> doctor.getId().equals(id)))
                remove = true;
            for (Department department : hospital.getDepartments()) {
                if (department.getDoctors().removeIf(doctor -> doctor.getId().equals(id)))
                    remove1 = true;
            }
        }
        if (remove1 || remove) System.out.println("Successfully deleted✅");
        else System.out.println("Not found Doctor ID❗️");
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        for (Hospital hospital : doctorDao.getAllHospital()) {
            for (Doctor doctor1 : hospital.getDoctors()) {
                if (doctor1.getId().equals(id)) {
                    doctor1.setFirstName(doctor.getFirstName());
                    return "Successfully updated";
                }
            }
        }
        return "Not found id";
    }
}

