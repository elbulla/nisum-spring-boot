package com.example.nisumassignment.repositories;

import com.example.nisumassignment.model.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * @author alex.quezada@42lines.net
 */
public interface EmployeeRepository  extends CrudRepository<Employee, Long> {
	
	Employee findEmployeeById(Long id);
	
	Iterable<Employee> findEmployeeByDepartment_Id(Long departmentId);

	Iterable<Employee> findEmployeeByDepartment_InternalId(String internalId);

}
