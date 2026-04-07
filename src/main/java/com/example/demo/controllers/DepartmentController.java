package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Department;
import com.example.demo.services.DepartmentService;

@RestController
@RequestMapping("/api/departments")

public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	@GetMapping
	public List<Department> getAllDepartments() {
		return departmentService.getAllDepartments();
	}
	
	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable int id) {
		return departmentService.getDepartmentById(id);
	}
	
	@PostMapping
	public Department addDepartment(@RequestBody Department department) {
		return departmentService.addDepartment(department);
	}
	
	@DeleteMapping("/{id}")
	public void deleteDepartment(@PathVariable int id) {
		departmentService.deleteDepartment(id);

}
}