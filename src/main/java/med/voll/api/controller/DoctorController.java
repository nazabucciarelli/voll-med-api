package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/doctor")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    public ResponseEntity<ResponseDoctorData> registerDoctor(@RequestBody @Valid RegisterDoctorData registerDoctorData,
                                                             UriComponentsBuilder uriComponentsBuilder){
        Doctor doctor = new Doctor(registerDoctorData);
        doctorRepository.save(doctor);
        ResponseDoctorData responseDoctorData = new ResponseDoctorData(doctor);
        URI uri = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(responseDoctorData);
    }

    @GetMapping
    public ResponseEntity<Page<GetDoctorData>> getDoctors(@PageableDefault(size = 2) Pageable page){
        return ResponseEntity.ok(doctorRepository.findByActiveTrue(page).map(GetDoctorData::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDoctorData> getDoctorById(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        ResponseDoctorData responseDoctorData = new ResponseDoctorData(doctor);
        return ResponseEntity.ok(responseDoctorData);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponseDoctorData>  updateDoctor(@RequestBody @Valid UpdateDoctorData updateDoctorData){
        Doctor doctor = doctorRepository.getReferenceById(updateDoctorData.id());
        doctor.updateData(updateDoctorData);
        return ResponseEntity.ok(new ResponseDoctorData(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity  deleteDoctor(@PathVariable Long id){
        Doctor doctor = doctorRepository.getReferenceById(id);
        doctor.deactiveDoctor();
        return ResponseEntity.noContent().build();
    }
}


