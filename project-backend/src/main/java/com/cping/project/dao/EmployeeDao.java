
package com.cping.project.dao;

import com.cping.project.bean.Employee;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDao {


    public List<Employee> getAllEmployee();

    public int addEmployee(Employee employee);

    public int deleteEmployee(int employee_id);

    public Employee getUpdateEmployee(int employee_id);

    public int editEmployee(Employee employee);
}