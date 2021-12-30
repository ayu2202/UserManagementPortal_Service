package com.iwork.usermanagement.service;

import com.iwork.usermanagement.entity.Customer;
import com.iwork.usermanagement.entity.User;
import com.iwork.usermanagement.exceptions.IworkException;

import java.util.List;

public interface UserService {

    public User login(User user) throws IworkException;

    public User register(User user) throws IworkException;

    public Boolean addCustomers(String emailId, Customer customers) throws IworkException;
    public Boolean removeCustomer(String emailId, Customer customer) throws IworkException;
    public Boolean updateCustomer(String emailId, List<Customer> updatedCustomers) throws IworkException;

}
