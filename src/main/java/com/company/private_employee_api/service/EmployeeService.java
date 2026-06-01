package com.company.private_employee_api.service;

import java.util.List;

import com.company.private_employee_api.dto.EmployeeDTO;

public interface EmployeeService {
	EmployeeDTO createEmployee(EmployeeDTO employeeDTO);

	List<EmployeeDTO> getAllEmployees();
	
	EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

    void deleteEmployee(Long id);
}
