package med.voll.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.voll.api.domain.appointment.BookAppointmentData;
import med.voll.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatient implements AppointmentValidator{
    @Autowired
    private PatientRepository patientRepository;

    public void validate(BookAppointmentData bookAppointmentData){
        if(bookAppointmentData.patientId() == null)
            return;
        boolean active = patientRepository.findByActiveTrue(bookAppointmentData.patientId());
        if(!active){
            throw new ValidationException("Patient is not active");
        }
    }
}
