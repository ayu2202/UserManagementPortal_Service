package com.iwork.usermanagement.service.impl;

import com.iwork.usermanagement.entity.Customer;
import com.iwork.usermanagement.entity.User;
import com.iwork.usermanagement.exceptions.IworkException;
import com.iwork.usermanagement.repository.UserRepository;
import com.iwork.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Override
    public User login(User user) throws IworkException {
        Optional<User> userOpt = userRepo.findUserByEmailIdAndPassword(user.getEmailId(), user.getPassword());
        User loggedInUser = userOpt.orElseThrow(() -> new IworkException("User.LOGIN_FAILED"));

        return loggedInUser;
    }

    @Override
    public User register(User user) throws IworkException {
        User userFetched = userRepo.findByEmailId(user.getEmailId());
        if(userFetched == null)
            return userRepo.save(user);
        else {
            throw new IworkException("User.USER_ALREADY_EXISTS");
        }
    }

    @Override
    public Boolean addCustomers(String emailId, Customer customer) throws IworkException {
        User user = userRepo.findByEmailId(emailId);
        if(user == null)
            throw new IworkException("User.USER_NOT_FOUND");
        List<Customer> custList = new ArrayList<>();
        if(user.getCustomers() != null) {
            custList = user.getCustomers();
        }
        custList.add(customer);
        user.setCustomers(custList);
        userRepo.save(user);
        return true;
    }

    @Override
    public Boolean removeCustomer(String emailId, Customer customer) throws IworkException {
        User user = userRepo.findByEmailId(emailId);
        if(user == null)
            throw new IworkException("User.USER_NOT_FOUND");
        List<Customer> fetchedCustomerList = user.getCustomers();
        List<Customer> custList = new ArrayList<>();
        fetchedCustomerList.forEach(cust -> {
            if(!cust.getCustomerName().equalsIgnoreCase(customer.getCustomerName()) && !cust.getCity().equalsIgnoreCase(customer.getCity())) {
                custList.add(cust);
            }
        });
        user.setCustomers(custList);
        userRepo.save(user);
        return true;
    }

    @Override
    public Boolean updateCustomer(String emailId, List<Customer> updatedCustomers) throws IworkException {
        User user = userRepo.findByEmailId(emailId);
        if(user == null)
            throw new IworkException("User.USER_NOT_FOUND");
        user.setCustomers(updatedCustomers);
        userRepo.save(user);
        return true;
    }


}
