package service.impl;

import dao.impl.HospitalDaoImpl;
import model.Hospital;
import model.Patient;
import myException.NotFoundException;
import service.HospitalService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {
    private final HospitalDaoImpl hospitalDao;

    public HospitalServiceImpl(HospitalDaoImpl hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @Override
    public String addHospital(Hospital hospital) {
        if (hospital != null) {
            hospitalDao.add(hospital);
            return "Successfully added";
        }
        return "Hospital is null";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
            return hospitalDao.getHospitalById(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAll();

    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        try {
            return hospitalDao.getHospitalById(id).getPatients();
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        try {
            hospitalDao.removeById(id);
            return "Successfully deleted";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> hospitalMap = new HashMap<>();
        try {
            for (Hospital hospital : hospitalDao.getAll()) {
                if (hospital.getAddress().equalsIgnoreCase(address)) {
                    hospitalMap.put(hospital.getAddress(), hospital);
                    return hospitalMap;
                }
            }throw new NotFoundException("Not found address");
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
