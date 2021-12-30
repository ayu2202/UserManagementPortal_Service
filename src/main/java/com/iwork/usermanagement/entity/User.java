package com.iwork.usermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document(collection = "User")
@Data
@AllArgsConstructor
public class User {

    @Id
    private String id;
    private String name;
    private String emailId;
    private String password;
    private List<Customer> customers;
}
