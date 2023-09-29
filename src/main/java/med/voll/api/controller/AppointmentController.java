package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.Appointment;
import med.voll.api.domain.appointment.BookAppointmentData;
import med.voll.api.domain.appointment.BookAppointmentService;
import med.voll.api.domain.appointment.DetailsAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ResponseBody
@RequestMapping("/appointments")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private BookAppointmentService bookAppointmentService;

    @PostMapping
    @Transactional
    public ResponseEntity<DetailsAppointmentData> book(@RequestBody BookAppointmentData bookAppointmentData){
        DetailsAppointmentData response = bookAppointmentService.book(bookAppointmentData);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsAppointmentData> getById(@PathVariable Long id){
        DetailsAppointmentData detailsAppointmentData =  bookAppointmentService.getReferenceById(id);
        if(detailsAppointmentData == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(detailsAppointmentData);
    }
}
