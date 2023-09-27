package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.AppointmentRepository;
import med.voll.api.domain.appointment.BookAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAppointment implements AppointmentValidator{
    @Autowired
    private AppointmentRepository appointmentRepository;
    public void validate(BookAppointmentData bookAppointmentData){
        if(bookAppointmentData.doctorId() == null){
            return;
        }
        boolean doctorWithAppointment = appointmentRepository.existsByDoctorIdAndAppointmentDate(bookAppointmentData.doctorId(),
                bookAppointmentData.date());

        if(doctorWithAppointment){
            throw new ValidationException("Doctor already has an appointment that schedule.");
        }
    }
}
