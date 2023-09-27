package med.voll.api.domain.appointment.validations;

import med.voll.api.domain.appointment.BookAppointmentData;

public interface AppointmentValidator {

    public void validate(BookAppointmentData bookAppointmentData);
}
