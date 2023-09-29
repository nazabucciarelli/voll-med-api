package med.voll.api.domain.doctor;

import med.voll.api.domain.address.AddressData;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.RegisterPatientData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    TestEntityManager em;

    @Test
    @DisplayName("deberia retornar nulo cuando el medico se encuentre en consulta con otro paciente en ese horario")
    void getSpecialistDoctorOnDate1() {
        // Given
        var nextMonday10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        Doctor doctor = registerDoctor("Nazareno Bucciarelli","nb@gmail.com","44532312",Speciality.CARDIOLOGIA);
        Patient patient= registerPatient("Jose Peralta", "jp@gmail.com","431345325");
        registerAppointment(doctor,patient,nextMonday10AM);
        // When
        var freeDoctor = doctorRepository.getSpecialistDoctorOnDate(Speciality.CARDIOLOGIA,nextMonday10AM);
        // Then
        assertThat(freeDoctor).isNull();
    }

    @Test
    @DisplayName("deberia retornar un médico cuando realice la consulta en la base de datos para ese horario")
    void getSpecialistDoctorOnDate2() {
        var nextMonday10AM = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        Doctor doctor = registerDoctor("Nazareno Bucciarelli","nb@gmail.com","44532312",Speciality.CARDIOLOGIA);
        var freeDoctor = doctorRepository.getSpecialistDoctorOnDate(Speciality.CARDIOLOGIA,nextMonday10AM);
        assertThat(freeDoctor).isEqualTo(doctor);
    }

    private Patient registerPatient(String name, String email, String id) {
        Patient p = new Patient(patientData(name,email,id));
        em.persist(p);
        return p;
    }

    private Doctor registerDoctor(String name, String email, String id, Speciality speciality) {
        Doctor d = new Doctor(doctorData(name,email,id,speciality));
        em.persist(d);
        return d;
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime nextMonday10AM) {
        em.persist(new Appointment(null,doctor,patient,nextMonday10AM));
    }

    private RegisterDoctorData doctorData(String name, String email, String id, Speciality speciality){
        return new
                RegisterDoctorData(name,email,id,"32425436",speciality,new AddressData("San Juan 222",
                "District","City","Test","323"));
    }

    private RegisterPatientData patientData(String name,String email,String id){
        return new RegisterPatientData(name,"3234234",email,id,new AddressData("San Juan 222",
                "District","City","Test","323"));
    }
}