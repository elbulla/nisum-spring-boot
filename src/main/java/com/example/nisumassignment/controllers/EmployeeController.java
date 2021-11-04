package com.example.nisumassignment.controllers;

import com.example.nisumassignment.model.Department;
import com.example.nisumassignment.model.Employee;
import com.example.nisumassignment.repositories.DepartmentRepository;
import com.example.nisumassignment.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author alex.quezada@42lines.net
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository; 

	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable Long id) {
		return employeeRepository.findEmployeeById(id);
	}

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		if (employee.getDepartment().getId() != null) {
			Department department = departmentRepository.findDepartmentById(employee.getDepartment().getId());
			if (department == null) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Department not found.");
			}
			employee.setDepartment(department);
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "DepartmentId is required");
		}
		employeeRepository.save(employee);
		
		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}

	@GetMapping
	public Iterable<Employee> getEmployees(@RequestParam(required = false) Long departmentId) {
		if (departmentId != null) {
			return employeeRepository.findEmployeeByDepartment_Id(departmentId);
		} else {
			return employeeRepository.findAll();
		}
	}

	@GetMapping("/dept-internal-id/{internalId}")
	public Iterable<Employee> getEmployees(@PathVariable String internalId) {
		return employeeRepository.findEmployeeByDepartment_InternalId(internalId);
	}
}
