package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.BookAppointmentData;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class WorkingTimeHospital implements AppointmentValidator{

    public void validate(BookAppointmentData bookAppointmentData){
        boolean sunday = DayOfWeek.SUNDAY.equals(bookAppointmentData.date().getDayOfWeek());
        boolean outOfTime = bookAppointmentData.date().getHour() < 7 || bookAppointmentData.date().getHour() > 19;
        if(sunday || outOfTime){
            throw new ValidationException("The hospital works from Mondays to Saturdays from 07:00 AM to 19:00 PM");
        }
    }
}
