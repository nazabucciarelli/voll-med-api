package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DetailsAppointmentData(Long id, Long doctorId, Long patientId, LocalDateTime date) {

    public DetailsAppointmentData(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(),appointment.getPatient().getId(),appointment.getAppointmentDate());
    }
}
