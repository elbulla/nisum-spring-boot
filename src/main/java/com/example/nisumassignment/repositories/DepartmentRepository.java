package com.example.nisumassignment.repositories;

import com.example.nisumassignment.model.Department;
import org.springframework.data.repository.CrudRepository;

/**
 * @author alex.quezada@42lines.net
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {
	
	Department findDepartmentById(Long id);
}
