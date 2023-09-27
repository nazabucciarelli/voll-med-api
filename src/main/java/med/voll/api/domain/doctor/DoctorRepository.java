package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor,Long> {
    Page<Doctor> findByActiveTrue(Pageable page);

    @Query("SELECT D FROM Doctor D WHERE D.active = true AND D.speciality=:speciality AND D.id NOT IN " +
            "(SELECT A.doctor.id FROM Appointment A WHERE A.appointmentDate=:date) ORDER BY RAND() LIMIT 1")
    Doctor getSpecialistDoctorOnDate(Speciality speciality, LocalDateTime date);

    boolean existsByIdAndActiveTrue(Long doctorId);
}
