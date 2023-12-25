package service.impl;

import dao.impl.PatientDaoImpl;
import model.Doctor;
import model.Patient;
import myException.NotFoundException;
import service.GenericService;
import service.PatientService;

import java.util.*;

public class PatientServiceImpl implements GenericService<Patient>, PatientService {
    private final PatientDaoImpl patientDao;

    public PatientServiceImpl(PatientDaoImpl patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            return patientDao.add(hospitalId, patient);
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public void removeById(Long id) {
        try {
            if (patientDao.removeById(id)) System.out.println("Successfully deleted");
            else throw new NotFoundException("Not found Patient id");
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Patient patient1 : patientDao.getAll()) {
            if (patient1.getId().equals(id)) {
                patient1.setFirstName(patient.getFirstName());
                return "Successfully updated";
            }
        }
        return "Not found id";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        try {
            patients.forEach(patient -> patientDao.add(id, patient));
            return "Successfully added";
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public Patient getPatientById(Long id) {
        try {
            for (Patient patient : patientDao.getAll()) {
                if (patient.getId().equals(id)) return patient;
            } throw  new NotFoundException("Not found patient id");
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        Map<Integer, List<Patient>> map = new HashMap<>();
        for (Patient patient : patientDao.getAll()) {
            if (!map.containsKey(patient.getAge())){
                map.put(patient.getAge(),new LinkedList<>(List.of(patient)));
            }else map.get(patient.getAge()).add(patient);
        }
        return map;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = patientDao.getAll();
        Comparator<Patient> comparator = Comparator.comparing(Patient::getAge);
        try {
           if("asc".equalsIgnoreCase(ascOrDesc)){
              patients.sort(comparator);
           } else if ("desc".equalsIgnoreCase(ascOrDesc)) {
              patients.sort(comparator.reversed());
           } else throw new NotFoundException("Should be write \"asc\" or \"desc\"");

        }catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return patients;
    }
}
