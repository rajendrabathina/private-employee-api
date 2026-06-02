package com.company.private_employee_api.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.private_employee_api.dto.EmployeeDTO;
import com.company.private_employee_api.entity.Employee;
import com.company.private_employee_api.exception.BadRequestException;
import com.company.private_employee_api.exception.ResourceNotFoundException;
import com.company.private_employee_api.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeDTO createEmployee(EmployeeDTO dto) {
		 // 1️⃣ Null check
	    if (dto.getEmail() == null || dto.getEmail().isEmpty()) {
	        throw new BadRequestException("Email cannot be empty");
	    }

	    // 2️⃣ Duplicate email check
	    if (employeeRepository.existsByEmail(dto.getEmail())) {
	        throw new BadRequestException("Employee with this email already exists");
	    }
	    try {
		Employee employee = new Employee(
				dto.getName(),
				dto.getEmail(),
				dto.getDepartment()
			);
			Employee savedEmployee = employeeRepository.save(employee);
			
			
			EmployeeDTO response = new EmployeeDTO();
			response.setId(savedEmployee.getId());
			response.setName(savedEmployee.getName());
			response.setEmail(savedEmployee.getEmail());
			response.setDepartment(savedEmployee.getDepartment());
			return response;
	    }catch (Exception e) {
	        throw new RuntimeException("Something went wrong while saving employee");
	    }
	}
	
	@Override
	public List<EmployeeDTO> getAllEmployees(){
		   List<Employee> employees = employeeRepository.findAll();

		    if (employees.isEmpty()) {
		        throw new ResourceNotFoundException("No employees found");
		    }
		return employees.stream()
	             .map(emp -> {
	                    EmployeeDTO dto = new EmployeeDTO();
					    dto.setId(emp.getId());
	                    dto.setName(emp.getName());
	                    dto.setEmail(emp.getEmail());
	                    dto.setDepartment(emp.getDepartment());
	                    return dto;
	             })
	             .collect(Collectors.toList());
	}

	@Override
	public EmployeeDTO getEmployeeByID(Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() ->
						new ResourceNotFoundException("Employee not found with id: " + id)
				);
		EmployeeDTO response = new EmployeeDTO();
		response.setId(employee.getId());
		response.setName(employee.getName());
		response.setEmail(employee.getEmail());
		response.setDepartment(employee.getDepartment());
		return response;
	}

	 @Override
	    public EmployeeDTO updateEmployee(Long id, EmployeeDTO dto) {

	        Employee employee = employeeRepository.findById(id)
	        		.orElseThrow(() ->
                    new ResourceNotFoundException("Employee not found with id: " + id)
            );
//	                .orElseThrow(() -> new RuntimeException("Employee not found"));

	        employee.setName(dto.getName());
	        employee.setEmail(dto.getEmail());
	        employee.setDepartment(dto.getDepartment());

	        Employee updatedEmployee = employeeRepository.save(employee);

	        EmployeeDTO response = new EmployeeDTO();
	        response.setName(updatedEmployee.getName());
	        response.setEmail(updatedEmployee.getEmail());
	        response.setDepartment(updatedEmployee.getDepartment());

	        return response;
	    }

	    @Override
	    public void deleteEmployee(Long id) {

	        Employee employee = employeeRepository.findById(id)
	        		.orElseThrow(() ->
                    new ResourceNotFoundException("Employee not found with id: " + id)
            );
//	                .orElseThrow(() -> new RuntimeException("Employee not found"));

	        employeeRepository.delete(employee);
	    }

}
