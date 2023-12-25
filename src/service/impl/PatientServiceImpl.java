package service.impl;

import dao.impl.PatientDaoImpl;
import model.Doctor;
import model.Hospital;
import model.Patient;
import myException.NotFoundException;
import service.GenericService;
import service.PatientService;

import java.util.*;

public class PatientServiceImpl implements PatientService {
    private final PatientDaoImpl patientDao;

    public PatientServiceImpl(PatientDaoImpl patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            for (Hospital hospital : patientDao.getAllHospital()) {
                if (hospital.getId().equals(hospitalId))
                    return patientDao.add(hospital, patient);
            }
            throw new NotFoundException("Not found id");
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public void removeById(Long id) {
        int count = 0;
        for (Hospital hospital : patientDao.getAllHospital()) {
            if (hospital.getPatients().removeIf(patient -> patient.getId().equals(id))){
                System.out.println("Successfully deleted✅");
               count++;}
        }
        if (count == 0) System.out.println("Not found id❗️");
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Hospital hospital : patientDao.getAllHospital()) {
            for (Patient patient1 : hospital.getPatients()) {
                if (patient1.getId().equals(id)) {
                    patient1.setFirstName(patient.getFirstName());
                    return "Successfully updated";
                }
            }
        }
        return "Not found id";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            for (Hospital hospital : patientDao.getAllHospital()) {
                if (hospital.getId().equals(id))
                    patients.forEach(patient -> patientDao.add(hospital, patient));
                return "Successfully added";
            }
            throw new NotFoundException("Not found Hospital id❗️");
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            for (Hospital hospital : patientDao.getAllHospital()) {
                for (Patient patient : hospital.getPatients()) {
                    if (patient.getId().equals(id)) return patient;
                }
            }  throw new NotFoundException("Not found Patient id");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        Map<Integer, List<Patient>> map = new HashMap<>();
        for (Hospital hospital : patientDao.getAllHospital()) {
            for (Patient patient : hospital.getPatients()) {
                if (!map.containsKey(patient.getAge())) {
                    map.put(patient.getAge(), new LinkedList<>(List.of(patient)));
                } else map.get(patient.getAge()).add(patient);
            }
        }
        return map;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = new ArrayList<>();
        patientDao.getAllHospital().forEach(hospital -> patients.addAll(hospital.getPatients()));
        Comparator<Patient> comparator = Comparator.comparing(Patient::getAge);
        try {
            if ("asc".equalsIgnoreCase(ascOrDesc)) {
                patients.sort(comparator);
            } else if ("desc".equalsIgnoreCase(ascOrDesc)) {
                patients.sort(comparator.reversed());
            } else throw new NotFoundException("Should be write \"asc\" or \"desc\"");

        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return patients;
    }
}
