package com.company.private_employee_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.private_employee_api.dto.EmployeeDTO;
import com.company.private_employee_api.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("createEmployee")
	public EmployeeDTO createEmployee(@RequestBody EmployeeDTO dto) {
		return employeeService.createEmployee(dto);
	}
	
	@GetMapping("getallEmployees")
	public List<EmployeeDTO> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@PutMapping("/update/{id}")
    public EmployeeDTO updateEmployee(
            @PathVariable Long id,
            @RequestBody EmployeeDTO dto) {
        return employeeService.updateEmployee(id, dto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "Employee deleted successfully";
    }
}
