package com.cs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cs.model.Employee;

@Repository
public class EmployeeDAO {
	@Autowired
	private DataSource dataSource;

	public void addEmployee(Employee employee) {
		String sql = "INSERT INTO EMPLOYEES (ID, NAME, DEPARTMENT, SALARY) VALUES (?, ?, ?, ?)";

		try (Connection con = dataSource.getConnection(); 
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, employee.getId());
			ps.setString(2, employee.getName());
			ps.setString(3, employee.getDepartment());
			ps.setDouble(4, employee.getSalary());
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getAllEmployees() {
		List<Employee> list = new ArrayList<>();
		String sql = "SELECT * FROM EMPLOYEES";

		try (Connection con = dataSource.getConnection();
			 PreparedStatement ps = con.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery()) {

			while (rs.next()) {
				Employee emp = new Employee(rs.getLong("ID"), 
						rs.getString("NAME"),
						rs.getString("DEPARTMENT"),
			            rs.getDouble("SALARY")
			            );
				list.add(emp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void updateEmployeeSalary(long id, double newSalary) {
		String sql = "UPDATE EMPLOYEES SET SALARY=? WHERE ID=?";

		try (Connection con = dataSource.getConnection(); 
		     PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setDouble(1, newSalary);
			ps.setLong(2, id);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteEmployee(long id) {
		String sql = "DELETE FROM EMPLOYEES WHERE ID=?";

		try (Connection con = dataSource.getConnection(); 
			 PreparedStatement ps = con.prepareStatement(sql)) {

			ps.setLong(1, id);
			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
