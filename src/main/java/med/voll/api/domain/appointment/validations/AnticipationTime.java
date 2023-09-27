package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.BookAppointmentData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AnticipationTime implements AppointmentValidator {

    public void validate(BookAppointmentData bookAppointmentData){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime appointmentTime = bookAppointmentData.date();

        boolean differenceOf30Min = Duration.between(now,appointmentTime).toMinutes() >= 30;
        if(!differenceOf30Min){
            throw new ValidationException("Bookings may be done 30 minutes earlier of the appointment time.");
        }

    }
}
