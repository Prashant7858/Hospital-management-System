package com.example.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Doctor;
import com.example.demo.services.DoctorService;

@RestController
@RequestMapping("api/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @PostMapping(consumes = {"multipart/form-data"})
    public Doctor addDoctor(
            @RequestParam("name") String name,
            @RequestParam("specialization") String specialization,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {

        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setSpecialization(specialization);
        doctor.setPhone(phone);
        doctor.setEmail(email);

        if (photo != null && !photo.isEmpty()) {
            String fileName = photo.getOriginalFilename();

            // Absolute path inside project directory
            Path uploadPath = Paths.get(System.getProperty("user.dir"), "uploads");
            Files.createDirectories(uploadPath);  // create folder if it does not exist

            Path filePath = uploadPath.resolve(fileName);
            photo.transferTo(filePath.toFile());

            doctor.setPhoto("/uploads/" + fileName); // save relative path in DB
        }

        return doctorService.addDoctor(doctor);
    }

    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
    }
}
