package com.cs;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cs.controller.EmployeeController;
import com.cs.model.Employee;

@SpringBootApplication
public class BootEmployeeCrudApplication implements CommandLineRunner{
	@Autowired
	private EmployeeController controller;

	public static void main(String[] args) {
		 SpringApplication.run(BootEmployeeCrudApplication.class, args);
	}

	public void run(String... args) {

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\n=== EMPLOYEE MANAGEMENT SYSTEM ===");
			System.out.println("1. Add Employee");
			System.out.println("2. View All Employees");
			System.out.println("3. Update Salary");
			System.out.println("4. Delete Employee");
			System.out.println("5. Exit");
			System.out.print("Enter choice: ");

			int ch = 0;
            try {
                ch = sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("\u001B[31mInvalid input! Please enter a number between 1-5.\u001B[0m");
                sc.nextLine(); 
                continue;
            }

			switch (ch) {
			case 1:
				System.out.print("Enter ID: ");
				long id = sc.nextLong();

				System.out.print("Enter Name: ");
				String name = sc.next();

				System.out.print("Enter Department: ");
				String dept = sc.next();

				System.out.print("Enter Salary: ");
				double sal = sc.nextDouble();

				controller.addEmployee(new Employee(id, name, dept, sal));
				break;

			case 2:
				controller.viewAllEmployees();
				break;

			case 3:
				System.out.print("Enter Employee ID: ");
				long id2 = sc.nextLong();
				System.out.print("Enter new Salary: ");
				double newSal = sc.nextDouble();
				controller.updateEmployeeSalary(id2, newSal);
				break;

			case 4:
				System.out.print("Enter Employee ID: ");
				long id3 = sc.nextLong();
				sc.nextLine(); 
				// Confirmation prompt
			    System.out.print("\u001B[33mAre you sure, you want to delete Employee ID " + id3 + "? (Y/N): \u001B[0m");
			    String confirm = sc.nextLine().trim();

			    if (confirm.equalsIgnoreCase("Y")) {
			        controller.deleteEmployee(id3);
			        break;
			    } else if (confirm.equalsIgnoreCase("N")) {
			        System.out.println("Deletion cancelled.");
			        break;
			    } else {
			        System.out.println("Invalid input! Please enter Y or N.");
			    }
			    break;

			case 5:
				System.out.println("\u001B[33mExiting...");
				return;

			default:
				System.out.println("\u001B[31mInvalid Choice!");
			}
		}
	}
}
