package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Department;
import com.example.demo.entities.Doctor;
import com.example.demo.repositories.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<Department> getAllDepartments(){
		return departmentRepository.findAll();
	}
	
	public Department getDepartmentById(int id) {
		return departmentRepository.findById(id).orElse(null);
	}
	
	public Department addDepartment(Department department) {

	    // Set the department reference in each doctor
	    if (department.getDoctors() != null) {
	        for (Doctor doctor : department.getDoctors()) {
	            doctor.setDepartment(department);
	        }
	    }

	    // Save department along with doctors
	    return departmentRepository.save(department);
	}

	
	public void deleteDepartment(int id) {
		departmentRepository.deleteById(id);
	}

}
