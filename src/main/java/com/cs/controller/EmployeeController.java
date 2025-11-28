package com.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.cs.model.Employee;
import com.cs.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	public void addEmployee(Employee employee) {
		employeeService.addEmployee(employee);
		System.out.println("\u001B[32mEmployee Added Successfully.\u001B[0m");
	}

	public void viewAllEmployees() {
		List<Employee> list = employeeService.getAllEmployees();
		list.forEach(System.out::println);
	}

	public void updateEmployeeSalary(long id, double newSalary) {
		employeeService.updateEmployeeSalary(id, newSalary);
		System.out.println("\u001B[32mSalary Updated.\u001B[0m");
	}

	public void deleteEmployee(long id) {
		employeeService.deleteEmployee(id);
		System.out.println("\u001B[32mEmployee Deleted Succesfully.\u001B[0m");
	}
}
