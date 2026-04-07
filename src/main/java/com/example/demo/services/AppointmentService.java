package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Appointment;
import com.example.demo.repositories.AppointmentRepository;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository appointmentRepository;
	
	public List<Appointment> getAllAppointments(){
		return appointmentRepository.findAll();
	}
	
	public Appointment addAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
	}
     public Appointment getAppointmentById(int id) {
    	 return appointmentRepository.findById(id).orElse(null);
     }
     
     public void deleteAppointment(int id) {
    	 appointmentRepository.deleteById(id);
     }
}
