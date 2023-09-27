package med.voll.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.doctor.Speciality;

import java.time.LocalDateTime;

public record BookAppointmentData(Long id, @NotNull Long doctorId, @NotNull Long patientId, @NotNull @Future
                                  LocalDateTime date, Speciality speciality) {
}
