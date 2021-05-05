
package com.cping.project.controller;

import com.cping.project.bean.Appointment;
import com.cping.project.bean.Employee;
import com.cping.project.common.Result;
import com.cping.project.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class EmployeeController {
    @Autowired
    EmployeeDao employeeDao;

    @PostMapping("/employee")
    public Result insertAddEmployee(@RequestBody Employee employee) {
        int i = employeeDao.addEmployee(employee);
        System.out.println(i);
        return Result.succ(200, "success", null);
    }

    @GetMapping("allEmployee")
    public Result getAllEmployee() {
        List<Employee> allEmployee = employeeDao.getAllEmployee();
        return Result.succ(allEmployee);
    }

    @DeleteMapping("/deleteEmployee/{employee_id}")
    public String deleteEmployee(@PathVariable("employee_id") int employee_id) {
        int i = employeeDao.deleteEmployee(employee_id);
        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/getEmployee/{employee_id}")
    public Result getUpdateEmployee(@PathVariable("employee_id") int employee_id){
        Employee employee = employeeDao.getUpdateEmployee(employee_id);
        return Result.succ(employee);
    }

    @PutMapping("/editEmployee")
    public String editEmployee(@RequestBody Employee employee) {
        int i = employeeDao.editEmployee(employee);
        return i > 0 ? "success" : "error";
    }
}