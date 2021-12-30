package com.iwork.usermanagement.controller;

import com.iwork.usermanagement.entity.Customer;
import com.iwork.usermanagement.entity.User;
import com.iwork.usermanagement.exceptions.IworkException;
import com.iwork.usermanagement.repository.UserRepository;
import com.iwork.usermanagement.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserService service;

    @GetMapping("/getall")
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws IworkException {
        return new ResponseEntity<>(service.register(user), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) throws IworkException {
        return new ResponseEntity<>(service.login(user), HttpStatus.OK);
    }

    @PostMapping("/customer/add/{emailId}")
    public ResponseEntity<Boolean> addCustomer(@PathVariable("emailId") String emailId, @RequestBody Customer customer) throws IworkException {
        return new ResponseEntity<>(service.addCustomers(emailId, customer), HttpStatus.OK);
    }

    @PostMapping("/customer/remove/{emailId}")
    public ResponseEntity<Boolean> removeCustomer(@PathVariable("emailId") String emailId, @RequestBody Customer customer) throws IworkException {
        return new ResponseEntity<>(service.removeCustomer(emailId, customer), HttpStatus.OK);
    }

    @PutMapping("/customer/update/{emailId}")
    public ResponseEntity<Boolean> updateCustomer(@PathVariable("emailId") String emailId, @RequestBody List<Customer> customers) throws IworkException {
        return new ResponseEntity<>(service.updateCustomer(emailId, customers), HttpStatus.OK);
    }

    @GetMapping("/get/{emailId}")
    public ResponseEntity<User> getUser(@PathVariable("emailId") String emailId) {
        return new ResponseEntity<>(userRepo.findByEmailId(emailId), HttpStatus.OK);
    }

}
