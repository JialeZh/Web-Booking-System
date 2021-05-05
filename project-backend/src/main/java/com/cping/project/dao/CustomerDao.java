package com.cping.project.dao;

import com.cping.project.bean.Customer;
import com.cping.project.bean.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDao {


    public List<Customer> getAllCustomer();

    public int addCustomer(Customer customer);

    public int deleteCustomer(int customer_id);

    public Customer getUpdateCustomer(int customer_id);

    public int editCustomer(Customer customer);

}
