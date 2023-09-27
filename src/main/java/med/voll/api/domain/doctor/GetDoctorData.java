package med.voll.api.domain.doctor;

public record GetDoctorData(Long id,String name,Speciality speciality,String dni,String email) {

    public GetDoctorData(Doctor doctor){
        this(doctor.getId(),doctor.getName(),doctor.getSpeciality(),doctor.getDni(),doctor.getEmail());
    }
}
