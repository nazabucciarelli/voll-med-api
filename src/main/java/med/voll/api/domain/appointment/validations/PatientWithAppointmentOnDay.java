package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.BookAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientWithAppointmentOnDay implements AppointmentValidator{

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(BookAppointmentData bookAppointmentData){
        if(bookAppointmentData.patientId() == null){
            throw new ValidationException("Patient id is null");
        }
        LocalDateTime firstSchedule = bookAppointmentData.date().withHour(7); // Modifica la hora de la fecha a las 7 am
        LocalDateTime lastSchedule = bookAppointmentData.date().withHour(18);

        Boolean patientWithAppointment = appointmentRepository.existsByPatientIdAndAppointmentDateBetween(
                bookAppointmentData.patientId(),
                firstSchedule, lastSchedule);


        if(patientWithAppointment){
            throw new ValidationException("Patient already has an appointment that day.");
        }
    }

}
