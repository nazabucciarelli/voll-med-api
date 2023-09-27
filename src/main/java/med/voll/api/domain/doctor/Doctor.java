package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;


@Table(name="doctors")
@Entity(name="Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String dni;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Address address;
    private Boolean active = true;

    public Doctor(RegisterDoctorData registerDoctorData) {
        this.name = registerDoctorData.name();
        this.email = registerDoctorData.email();
        this.phoneNumber = registerDoctorData.phoneNumber();
        this.dni = registerDoctorData.dni();
        this.speciality = registerDoctorData.speciality();
        this.address = new Address(registerDoctorData.address());
    }
    public void updateData(UpdateDoctorData updateDoctorData) {
        if(updateDoctorData.name() != null){
            this.name = updateDoctorData.name();
        }
        if(updateDoctorData.dni() != null){
            this.dni = updateDoctorData.dni();
        }
        if(updateDoctorData.address() != null){
            this.address = this.address.updateAddress(updateDoctorData.address());
        }
    }

    public void deactiveDoctor() {
        this.active = false;
    }
}
