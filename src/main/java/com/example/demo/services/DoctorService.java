package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Doctor;
import com.example.demo.repositories.DoctorRepository;

@Service
public class DoctorService {
	
	@Autowired
	DoctorRepository doctorRepository;
	
	public List<Doctor> getAllDoctors() {
		return doctorRepository.findAll();
	}
	
	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public Doctor getDoctorById(int id) {
		return doctorRepository.findById(id).orElse(null);
	}
	
	public void deleteDoctor(int id) {
		doctorRepository.deleteById(id);
	}

}
