package med.voll.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Boolean existsByPatientIdAndAppointmentDateBetween(Long patientId, LocalDateTime firstSchedule, LocalDateTime lastSchedule);

    boolean existsByDoctorIdAndAppointmentDate(Long doctorId, LocalDateTime date);
}
