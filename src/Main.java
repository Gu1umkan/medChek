import dao.impl.DepartmentDaoImpl;
import dao.impl.DoctorDaoImpl;
import dao.impl.HospitalDaoImpl;
import dao.impl.PatientDaoImpl;
import db.Database;
import enums.Gender;
import idGenerator.IDGenerator;
import model.Department;
import model.Doctor;
import model.Hospital;
import model.Patient;
import service.DepartmentService;
import service.DoctorService;
import service.HospitalService;
import service.PatientService;
import service.impl.DepartmentServiceImpl;
import service.impl.DoctorServiceImpl;
import service.impl.HospitalServiceImpl;
import service.impl.PatientServiceImpl;

import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        Database database =new Database();
        HospitalService hospitalService =new HospitalServiceImpl(new HospitalDaoImpl(database));
        DepartmentServiceImpl departmentService = new DepartmentServiceImpl(new DepartmentDaoImpl(database));
        DoctorServiceImpl doctorService = new DoctorServiceImpl(new DoctorDaoImpl(database));
        PatientServiceImpl patientService = new PatientServiceImpl(new PatientDaoImpl(database));
        outLoop:
      while (true){
          menu();
          try {
          switch (scanner.nextInt()) {
              case 0 ->{ break outLoop;}
              case 1 ->{
                  Hospital hospital = new Hospital();
                  System.out.print("Enter Hospital name: ");
                  hospital.setId(IDGenerator.idHospital());
                  hospital.setHospitalName(chekScanner());
                  System.out.print("Enter Hospital address: ");
                  hospital.setAddress(chekScanner());
                  System.out.println(hospitalService.addHospital(hospital));}
              case 2 -> {
                  System.out.print("Enter Hospital ID: ");
                  System.out.println(hospitalService.findHospitalById(chekLong()));
              }
              case 3-> System.out.println(hospitalService.getAllHospital());
              case 4->{
                  System.out.println("Enter Hospital ID:  ");
                  System.out.println(hospitalService.getAllPatientFromHospital(chekLong()));
              }
              case 5->{
                  System.out.println("Enter Hospital ID:  ");
                  System.out.println(hospitalService.deleteHospitalById(chekLong()));
              }
              case 6-> {
                  System.out.print("Enter Hospital address: ");
                  System.out.println(hospitalService.getAllHospitalByAddress(chekScanner()));
              }
              case 7->{
                  System.out.print("Enter Hospital ID: ");
                  Long id = chekLong();
                  System.out.print("Enter Department name: ");
                  Department department = new Department(chekScanner(), new ArrayList<>());
                  department.setId(IDGenerator.idDepartment());
                  System.out.println(departmentService.add(id,department));
              }
              case 8 ->{
                  System.out.print("Enter Department ID:");
                  departmentService.removeById(chekLong());
              }
              case 9->{
                  System.out.print("Enter Department ID: ");
                  Long id = chekLong();
                  System.out.print("Enter new Department name: ");
                  System.out.println(departmentService.updateById(id,new Department(chekScanner(),new ArrayList<>())));
              }
              case 10->{
                  System.out.print("Enter Hospital ID: ");
                  System.out.println(departmentService.getAllDepartmentByHospitalId(chekLong()));
              }
              case 11->{
                  System.out.print("Enter Department name:");
                  System.out.println(departmentService.findDepartmentByName(chekScanner()));
              }
              case 12->{
                  System.out.print("Enter Hospial ID: ");
                  Long id = chekLong();
                  Doctor doctor = new Doctor("Gulumkan","Uson kyzy", Gender.FEMALE,3);
                  System.out.println(doctor);
                  System.out.println(doctorService.add(id,doctor));
              }
              case 13->{
                  System.out.print("Enter Doctor ID: ");
                  doctorService.removeById(chekLong());
              }
              case 14->{
                  System.out.print("Enter Doctor ID: ");
                  Long id = chekLong();
                  Doctor doctor =new Doctor();
                  System.out.print("Enter new Doctor name: ");
                  doctor.setFirstName(chekScanner());
                  System.out.println(doctorService.updateById(id,doctor));
              }
              case 15 ->{
                  System.out.print("Enter Doctor ID:");
                  System.out.println(doctorService.findDoctorById(chekLong()));
              }
              case 16->{
                  System.out.print("Enter Department ID: ");
                  Long id =chekLong();
                  List<Long> ids = new ArrayList<>(Arrays.asList(1L,4L,3L));
                  System.out.println(doctorService.assignDoctorToDepartment(id, ids));
              }
              case 17->{
                  System.out.print("Enter Hospital ID: ");
                  System.out.println(doctorService.getAllDoctorsByHospitalId(chekLong()));
              }
              case 18 ->{
                  System.out.print("Enter Department ID: ");
                  System.out.println(doctorService.getAllDoctorsByDepartmentId(chekLong()));
              }
              case 19 ->{
                  System.out.print("Enter Hospital ID: ");
                  Patient patient = new Patient("Nurgazy","Temiraliev",19,Gender.MALE);
                  patient.setId(IDGenerator.idPatient());
                  System.out.println(patientService.add(chekLong(), patient));
              }
              case 20 ->{
                  System.out.print("Enter Patient ID: ");
                  patientService.removeById(chekLong());
              }
              case 21 -> {
                  System.out.print("Enter Patient ID: ");
                  Long id = chekLong();
                  Patient patient = new Patient();
                  patient.setFirstName("Nuraida");
                  System.out.println(patientService.updateById((id), patient));
              }
              case 22 ->{
                  System.out.print("Enter Hospital ID: ");
                  Long idHospital = chekLong();
                  Patient patient = new Patient("Guli","Satybaeva",18,Gender.FEMALE);
                  patient.setId(IDGenerator.idPatient());
                  Patient patient1 = new Patient( "Aliaskar","Temirbekov",22,Gender.MALE);
                  patient1.setId(IDGenerator.idPatient());
                  System.out.println(patientService.addPatientsToHospital(idHospital, new ArrayList<>(Arrays.asList(patient, patient1))));
              }
              case 23 -> {
                  System.out.print("Enter Patient ID: ");
                  System.out.println(patientService.getPatientById(chekLong()));
              }
              case 24 ->{
                  System.out.print("Enter Patient age: ");
                  System.out.println(patientService.getPatientByAge());
              }
              case 25 ->{
                  System.out.println("Enter \"asc\" for ascending sort or \" desc\" for descending sort");
                  System.out.println(patientService.sortPatientsByAge(chekScanner()));
              }
              default -> System.out.println("Invalid choice!");

          }
          }catch (InputMismatchException e){
              System.out.println("Enter number!!!");
          }
      }

    }
    public static void menu(){
        System.out.println("""
                🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢🟢                            
                                              MENU
                0 ->  Exit                                         13 -> Remove Doctor by ID
                1 ->  Add Hospital                                 14 -> Update Doctor by ID
                2 ->  Find Hospital by ID                          15 -> Find Doctor by ID
                3 ->  Get all Hospitals                            16 -> Assign Doctors to Department
                4 ->  Get all Patient from Hospital                17 -> Get all Doctors by Hospital ID
                5 ->  Delete Hospital by ID                        18 -> Get all Doctors by Department ID
                6 ->  Get all Hospitals by address                 19 -> Add Patient to Hospital by ID
                7 ->  Add Department to Hospital by ID             20 -> Remove Patient by ID
                8 ->  Remove Department by ID                      21 -> Update Patient by ID
                9 ->  Update Department by ID                      22 -> Add Patients to Hospital by ID
                10 -> Get all Department by Hospital ID            23 -> Get Patient by ID
                11 -> Find Department by name                      24 -> Get Patients by age
                12 -> Add Doctor to Hospital by ID                 25 -> Sort Patient by age
                Enter command ↩️  """);
    }
    public static String chekScanner() {
        while (true) {
            String scannerWord = new Scanner(System.in).nextLine();
            if (!scannerWord.isBlank()) {
                return scannerWord;
            } else {
                System.err.println(" Бош болуусу мүмкун эмес , маалымат киргизиңиз : ");
            }
        }
    }
    public static Long chekLong() {
        while (true) {
            try {
                return new Scanner(System.in).nextLong();
            } catch (InputMismatchException e) {
                System.out.print("long тибинде id киргиз: ");
            }
        }
    }

}