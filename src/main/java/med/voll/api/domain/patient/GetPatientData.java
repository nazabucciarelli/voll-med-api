package med.voll.api.domain.patient;

public record GetPatientData(String name,String phoneNumber,String email) {
    public GetPatientData(Patient patient){
        this(patient.getName(),patient.getPhoneNumber(),patient.getEmail());
    }
}
