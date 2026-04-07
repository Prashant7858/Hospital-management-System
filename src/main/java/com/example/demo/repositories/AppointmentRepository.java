package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{

}
