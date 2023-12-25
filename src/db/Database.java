package db;

import enums.Gender;
import idGenerator.IDGenerator;
import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Database {
    private List<Hospital> hospitals = new LinkedList<>(input());

    public Database() {

    }

    public List<Hospital> getHospitals() {
        return hospitals;
    }


    private List<Hospital>  input(){
         Doctor doctor = new Doctor("Мырзайым","Келдибекова", Gender.FEMALE,3);
         doctor.setId(IDGenerator.idDoctor());
         Doctor doctor1 = new Doctor("Aiturgan","Maksatbekova",Gender.FEMALE,5);
         doctor1.setId(IDGenerator.idDoctor());
         Doctor doctor2 = new Doctor("Nurlan","Abiibillaev",Gender.MALE,1);
         doctor2.setId(IDGenerator.idDoctor());
         Doctor doctor3 = new Doctor("Urmat","Taichikov",Gender.MALE,2);
         doctor3.setId(IDGenerator.idDoctor());
         Doctor doctor4 = new Doctor("Zaripbek","Dushoev",Gender.MALE,10);
         doctor4.setId(IDGenerator.idDoctor());
         Doctor doctor5 = new Doctor("Zhigit", "Turumbekov",Gender.MALE,7);
         doctor5.setId(IDGenerator.idDoctor());
         Doctor doctor6 = new Doctor("Mirlan","Arstanbekov",Gender.MALE,8);
         doctor6.setId(IDGenerator.idDoctor());


         Patient patient = new Patient("Adina","Aitmatova",21,Gender.FEMALE);
         patient.setId(IDGenerator.idPatient());
         Patient patient1 = new Patient("Aidana","Akmatova",20,Gender.FEMALE);
         patient1.setId(IDGenerator.idPatient());
         Patient patient2 = new Patient("Saikal","Akbaralieva",22,Gender.FEMALE);
         patient2.setId(IDGenerator.idPatient());
         Patient patient3 = new Patient("Kanymai","Alimbekova",18,Gender.FEMALE);
         patient3.setId(IDGenerator.idPatient());
         Patient patient4 = new Patient("Zarina","Kudainazarova",19,Gender.FEMALE);
         patient4.setId(IDGenerator.idPatient());
         Patient patient5 = new Patient("Albina","Satybaldieva",23,Gender.FEMALE);
         patient5.setId(IDGenerator.idPatient());
         Patient patient6 = new Patient("Zepa","Alapaeva",25,Gender.FEMALE);
         patient6.setId(IDGenerator.idPatient());
         Patient patient7 = new Patient("Kudaiberdi","Gapurov",24,Gender.MALE);
         patient7.setId(IDGenerator.idPatient());



         Department department = new Department("Surgery",new ArrayList<>());
         department.setId(IDGenerator.idDepartment());
         Department department1 = new Department("Traumatology",new ArrayList<>());
         department1.setId(IDGenerator.idDepartment());
         Department department2 = new Department("Сardiology",new ArrayList<>());
         department2.setId(IDGenerator.idDepartment());
         Department department3 = new Department("Therapy",new ArrayList<>(Arrays.asList(doctor6)));
         department3.setId(IDGenerator.idDepartment());
         Department department4 = new Department("Oncology",new ArrayList<>());
         department4.setId(IDGenerator.idDepartment());

    Hospital hospital = new Hospital("Миррахимов  ", "Тоголок Молдо 3",
            new ArrayList<>(Arrays.asList(department,department1)),
            new ArrayList<>(Arrays.asList(doctor,doctor1,doctor2)), new ArrayList<>(Arrays.asList(patient2,patient1,patient,patient7)));
    hospital.setId(IDGenerator.idHospital());
    Hospital hospital1 = new Hospital("Мамакеев","Cухомлинов 28",
            new ArrayList<>(Arrays.asList(department2,department3,department4)),
            new ArrayList<>(Arrays.asList(doctor3,doctor4,doctor5,doctor6)),
            new ArrayList<>(Arrays.asList(patient3,patient4,patient6,patient5)));
    hospital1.setId(IDGenerator.idHospital());
    Hospital hospital2= new Hospital("НЦКТ","Бишкек",new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
    hospital2.setId(IDGenerator.idHospital());
   return new LinkedList<>(List.of(hospital,hospital1,hospital2));
    }
}
