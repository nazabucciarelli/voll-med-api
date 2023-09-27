package med.voll.api.domain.appointment;

import med.voll.api.domain.appointment.validations.AppointmentValidator;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exceptions.IntegrityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookAppointmentService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private List<AppointmentValidator> appointmentValidatorList;

    public DetailsAppointmentData book(BookAppointmentData bookAppointmentData){
        if(!patientRepository.findById(bookAppointmentData.patientId()).isPresent()){
            throw new IntegrityException("Not found ID on table Patients");
        }
        if(bookAppointmentData.doctorId() != null && !doctorRepository.existsById(bookAppointmentData.doctorId())){
            throw new IntegrityException("Not found ID on table Doctors");
        }
        appointmentValidatorList.forEach(v -> v.validate(bookAppointmentData));
        Doctor doctor = selectDoctor(bookAppointmentData);
        if(doctor==null){
            throw new IntegrityException("There are not available doctors for this speciality and schedule");
        }
        Patient patient = patientRepository.findById(bookAppointmentData.patientId()).get();
        Appointment appointment = new Appointment(bookAppointmentData.id(), doctor,patient,bookAppointmentData.date());
        appointmentRepository.save(appointment);
        return new DetailsAppointmentData(appointment);
    }

    private Doctor selectDoctor(BookAppointmentData bookAppointmentData) {
        if(bookAppointmentData.doctorId() != null){
            return doctorRepository.getReferenceById(bookAppointmentData.doctorId());
        }
        if(bookAppointmentData.speciality() == null){
            throw new IntegrityException("You must select a speciality");
        }
        return doctorRepository.getSpecialistDoctorOnDate(bookAppointmentData.speciality(), bookAppointmentData.date());
    }
}
