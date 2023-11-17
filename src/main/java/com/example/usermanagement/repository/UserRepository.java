package com.example.usermanagement.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.example.usermanagement.entity.User;

@Repository
public interface UserRepository  extends MongoRepository<User, String>, UserRepositoryCustom{

}
