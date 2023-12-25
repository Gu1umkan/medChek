package idGenerator;

public class IDGenerator {
    private static long idForHospital = 0L;
    private static long idForDepartment = 0L;
    private static long idForDoctor = 0L;
    private static long idForPAtient = 0L;
    public static Long idHospital(){
        return ++idForHospital;
    }
    public static Long idDepartment(){
        return ++idForDepartment;
    }
    public static Long idDoctor(){
        return ++idForDoctor;
    }
    public static Long idPatient(){
        return ++idForPAtient;
    }

}
