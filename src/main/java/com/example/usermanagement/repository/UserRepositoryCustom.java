package com.example.usermanagement.repository;

import java.util.List;
import com.example.usermanagement.entity.User;

public interface UserRepositoryCustom {

  public List<User> getDetailsFromKeyword(String typeOfSearch, String keyword, Integer page , Integer size);

  List<User> getAllUserDetails(Integer page, Integer size);
}
