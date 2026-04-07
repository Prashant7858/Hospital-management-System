package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Patient;
import com.example.demo.services.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    // Constructor injection 
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // GET /api/patients  -> returns list of all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    // POST /api/patients -> create a new patient
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient saved = patientService.addPatient(patient);
        return ResponseEntity.created(URI.create("/api/patients/" + saved.getId())).body(saved);
    }

    // DELETE /api/patients/{id} -> delete by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id) {
        try {
            patientService.deletePatient(id);
            return ResponseEntity.noContent().build(); // 204
        } catch (org.springframework.dao.EmptyResultDataAccessException ex) {
            // thrown when deleteById can't find the id
            return ResponseEntity.notFound().build(); // 404
        } catch (Exception ex) {
            // generic fallback
            return ResponseEntity.status(500).build();
        }
    }
}
