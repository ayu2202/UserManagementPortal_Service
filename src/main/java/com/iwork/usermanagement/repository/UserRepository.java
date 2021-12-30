package com.iwork.usermanagement.repository;

import com.iwork.usermanagement.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findUserByEmailIdAndPassword(String emailId, String password);

    User findByEmailId(String emailId);

}
