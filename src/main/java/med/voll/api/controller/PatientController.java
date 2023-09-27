package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patient")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @PostMapping
    public ResponseEntity<ResponsePatientData> registerPatient(@RequestBody @Valid RegisterPatientData registerPatientData,
                                                               UriComponentsBuilder uriComponentsBuilder){
        Patient patient = new Patient(registerPatientData);
        patientRepository.save(patient);
        URI uri = uriComponentsBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponsePatientData(patient));
    }

    @GetMapping
    public ResponseEntity<Page<GetPatientData>> getPatients(@PageableDefault(size=3) Pageable page){
        return ResponseEntity.ok(patientRepository.findByActiveTrue(page).map(GetPatientData::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsePatientData> getPatients(@PathVariable Long id){
        Patient patient = patientRepository.getReferenceById(id);
        return ResponseEntity.ok(new ResponsePatientData(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ResponsePatientData> updatePatient(@RequestBody @Valid UpdatePatientData updatePatientData){
        Patient patient = patientRepository.getReferenceById(updatePatientData.id());
        patient.updateData(updatePatientData);
        ResponsePatientData responsePatientData = new ResponsePatientData(patient);
        return ResponseEntity.ok(responsePatientData);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id){
        Patient patient = patientRepository.getReferenceById(id);
        patient.deactivePatient();
        return ResponseEntity.noContent().build();
    }

}
