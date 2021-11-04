package com.example.nisumassignment.controllers;

import com.example.nisumassignment.model.Department;
import com.example.nisumassignment.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alex.quezada@42lines.net
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

	@Autowired
	private DepartmentRepository departmentRepository;

	@PostMapping
	public ResponseEntity<Department> addDepartment(@RequestBody Department department) {
		departmentRepository.save(department);
		return new ResponseEntity<>(department, HttpStatus.CREATED);
	}
	
	@GetMapping
	public Iterable<Department> getDepartments() {
		return departmentRepository.findAll();
	}

}
