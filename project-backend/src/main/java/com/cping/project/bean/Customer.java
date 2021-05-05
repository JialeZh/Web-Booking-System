package com.cping.project.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.sql.Time;

public class Customer {
    private int customer_id;
    private String customer_firstname;
    private String customer_lastname;
    private String phone_number;
    private String member_status;

    public Customer() {
    }

    public Customer(int customer_id, String customer_firstname, String customer_lastname, String phone_number, String member_status) {
        this.customer_id = customer_id;
        this.customer_firstname = customer_firstname;
        this.customer_lastname = customer_lastname;
        this.phone_number = phone_number;
        this.member_status = member_status;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_firstname() {
        return customer_firstname;
    }

    public void setCustomer_firstname(String customer_firstname) {
        this.customer_firstname = customer_firstname;
    }

    public String getCustomer_lastname() {
        return customer_lastname;
    }

    public void setCustomer_lastname(String customer_lastname) {
        this.customer_lastname = customer_lastname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getMember_status() {
        return member_status;
    }

    public void setMember_status(String member_status) {
        this.member_status = member_status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customer_id=" + customer_id +
                ", customer_firstname='" + customer_firstname + '\'' +
                ", customer_lastname='" + customer_lastname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", member_status='" + member_status + '\'' +
                '}';
    }
}
