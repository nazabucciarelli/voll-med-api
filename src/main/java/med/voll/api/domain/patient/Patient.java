package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.address.Address;

@Entity
@Table(name = "patients")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String dni;
    @Embedded
    private Address address;
    private Boolean active = true;

    public Patient(RegisterPatientData registerPatientData) {
        this.name = registerPatientData.name();
        this.phoneNumber = registerPatientData.phoneNumber();
        this.email = registerPatientData.email();
        this.dni = registerPatientData.dni();
        this.address = new Address(registerPatientData.address());
    }

    public void updateData(UpdatePatientData updatePatientData) {
        if(updatePatientData.name() != null){
            this.name = updatePatientData.name();
        }
        if(updatePatientData.email() != null){
            this.email = updatePatientData.email();
        }
        if(updatePatientData.phoneNumber() != null){
            this.phoneNumber = updatePatientData.phoneNumber();
        }
        if(updatePatientData.dni() != null){
            this.dni = updatePatientData.dni();
        }
        if(updatePatientData.address() != null){
            this.address = this.address.updateAddress(updatePatientData.address());
        }
    }

    public void deactivePatient() {
        this.active = false;
    }
}
