package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;

public record ResponseDoctorData(
        Long id,
        String name,
        String email,
        String dni,
        Speciality speciality,
        String phoneNumber,
        AddressData address
        ) {
    public ResponseDoctorData(Doctor doctor) {
        this(doctor.getId(),doctor.getName(),doctor.getEmail(),doctor.getDni(),doctor.getSpeciality(),
                doctor.getPhoneNumber(), new AddressData(doctor.getAddress()));
    }
}
