package com.cping.project.controller;

import com.alibaba.fastjson.JSON;
import com.cping.project.bean.Customer;
import com.cping.project.bean.User;
import com.cping.project.common.Result;
import com.cping.project.dao.CustomerDao;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CustomerController {
    @Autowired
    CustomerDao customerDao;

    @PostMapping("/customer")
    public Result insertAddCustomer(@RequestBody Customer customer) {
        int i = customerDao.addCustomer(customer);
        System.out.println(i);
        return Result.succ(200, "success", null);
    }

    @GetMapping("allCustomer")
    public Result getAllAppoint() {
        List<Customer> allCustomer = customerDao.getAllCustomer();
        return Result.succ(allCustomer);
    }


    @DeleteMapping("/deleteCustomer/{customer_id}")
    public String deleteCustomer(@PathVariable("customer_id") int customer_id) {
        int i = customerDao.deleteCustomer(customer_id);
        return i > 0 ? "success" : "error";
    }

    @RequestMapping("/getCustomer/{customer_id}")
    public Result getUpdateCustomer(@PathVariable("customer_id") int customer_id){
        Customer customer = customerDao.getUpdateCustomer(customer_id);
        return Result.succ(customer);
    }

    @PutMapping("/editCustomer")
    public String editCustomer(@RequestBody Customer customer) {
        int i = customerDao.editCustomer(customer);
        return i > 0 ? "success" : "error";
    }
}
