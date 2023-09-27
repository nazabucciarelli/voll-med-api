package med.voll.api.domain.patient;

import med.voll.api.domain.address.AddressData;

public record ResponsePatientData(Long id, String name, String phoneNumber, String email, String dni,
                                  AddressData address) {
    public ResponsePatientData(Patient patient) {
        this(patient.getId(),patient.getName(),patient.getPhoneNumber(),patient.getEmail(),patient.getDni(),
                new AddressData(patient.getAddress()));
    }
}
