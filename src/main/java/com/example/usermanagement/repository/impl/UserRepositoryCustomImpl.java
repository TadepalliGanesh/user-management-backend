package com.example.usermanagement.repository.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.repository.UserRepositoryCustom;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

  @Autowired
  MongoTemplate mongoTemplate;

  public List<User> getDetailsFromKeyword(String typeOfSearch, String keyword, Integer page , Integer size){
    Query query= new Query();
    query.with(PageRequest.of(page,size));
    if(!keyword.isEmpty())
    query.addCriteria(new Criteria().andOperator(Criteria.where(typeOfSearch).exists(true),
        Criteria.where(typeOfSearch).regex(keyword, "i")));
//    query.addCriteria(new Criteria().orOperator(Criteria.where("userName").is(keyword),
//        Criteria.where("emailId").is(keyword), Criteria.where("phoneNo").is(keyword), Criteria.where("_id").is(keyword)));
    return mongoTemplate.find(query, User.class , "USER_COLLECTION");
  }

  public List<User> getAllUserDetails(Integer page, Integer size){
    Query query= new Query();
    query.with(PageRequest.of(page,size));
    return mongoTemplate.find(query, User.class, "USER_COLLECTION");

  }

}
