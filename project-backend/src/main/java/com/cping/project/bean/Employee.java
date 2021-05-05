package com.cping.project.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class Employee {
    private int employee_id;
    private String employee_firstname;
    private String employee_lastname;
    private String username;
    //@DateTimeFormat(pattern = "yyyy-MM-dd")
    private String birthday;



    public Employee() {
    }

    public Employee(int employee_id, String employee_firstname, String employee_lastname, String username, String birthday) {
        this.employee_id = employee_id;
        this.employee_firstname = employee_firstname;
        this.employee_lastname = employee_lastname;
        this.username = username;
        this.birthday = birthday;

    }



    public int getEmployee_id(){
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_firstname() {
        return employee_firstname;
    }

    public void setEmployee_firstname(String employee_firstname) {
        this.employee_firstname = employee_firstname;
    }

    public String getEmployee_lastname() {
        return employee_lastname;
    }

    public void setEmployee_lastname(String employee_lastname) {
        this.employee_lastname = employee_lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /*    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }*/

    @Override
    public String toString(){
        return "Employee{" +
                "employee_id=" + employee_id +
                ", customer_firstname='" + employee_firstname + '\'' +
                ", customer_lastname='" + employee_lastname + '\'' +
                ", username=" + username +
                ", birthday=" + birthday +
                '}';
    }
}