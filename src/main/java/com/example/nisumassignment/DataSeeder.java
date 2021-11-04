package com.example.nisumassignment;

import com.example.nisumassignment.model.Department;
import com.example.nisumassignment.model.Employee;
import com.example.nisumassignment.repositories.DepartmentRepository;
import com.example.nisumassignment.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author alex.quezada@42lines.net
 */
@Component
public class DataSeeder {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@EventListener
	public void seedTestData(ContextRefreshedEvent event) {
		Department engineering = departmentRepository.save(new Department("Engineering", "123"));
		Department sales = departmentRepository.save(new Department("Sales", "124"));
		
		employeeRepository.save(new Employee("Alex", "Quezada", engineering));
		employeeRepository.save(new Employee("Luna", "Dog", engineering));
		employeeRepository.save(new Employee("Charlie", "Dog", sales));
	}
}
