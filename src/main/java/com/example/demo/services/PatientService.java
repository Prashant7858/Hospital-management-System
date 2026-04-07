package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Patient;
import com.example.demo.repositories.PatientRepository;

@Service
public class PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	public List<Patient> getAllPatients() {
		return  patientRepository.findAll();
	}
	
	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	public void deletePatient(int id) {
		patientRepository.deleteById(id);
	}

}
