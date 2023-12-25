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
          return  hospitalDao.add(hospital);
        }
        return "Hospital is null";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        try {
            for (Hospital hospital : hospitalDao.getAllHospital()) {
                if (hospital.getId().equals(id)) return hospital;
            }
            throw new NotFoundException("Not found hospital id");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAllHospital();

    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        try {
            for (Hospital hospital : hospitalDao.getAllHospital()) {
                if (hospital.getId().equals(id)) return hospital.getPatients();
            }
            throw new NotFoundException("Not found Hospital id");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        try {
            if (hospitalDao.getAllHospital().removeIf(hospital -> hospital.getId().equals(id)))
                return "Successfully deleted";
            else throw new NotFoundException("Not found hospital ID❗️");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> hospitalMap = new HashMap<>();
        try {
            for (Hospital hospital : hospitalDao.getAllHospital()) {
                if (hospital.getAddress().equalsIgnoreCase(address)) {
                    hospitalMap.put(hospital.getAddress(), hospital);
                    return hospitalMap;
                }
            }
            throw new NotFoundException("Not found address");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
