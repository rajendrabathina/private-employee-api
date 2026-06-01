package com.company.private_employee_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.private_employee_api.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	boolean existsByEmail(String email);
}
