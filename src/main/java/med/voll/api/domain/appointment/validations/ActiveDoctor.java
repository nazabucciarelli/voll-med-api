package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.BookAppointmentData;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveDoctor implements AppointmentValidator{
    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(BookAppointmentData bookAppointmentData){
        if(bookAppointmentData.doctorId() == null)
            return;
        boolean active = doctorRepository.existsByIdAndActiveTrue(bookAppointmentData.doctorId());
        if (!active)
            throw  new ValidationException("Doctor id "+ bookAppointmentData.doctorId() +" is not active on the system");
    }
}
