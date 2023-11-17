package com.example.usermanagement.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.usermanagement.entity.User;
import com.lowagie.text.BadElementException;

public interface UserService {

  public User storeUserDetails(String userName, String emailId , String phoneNumber, String password);

  public List<User> getDetails(String typeOfSearch, String keyword, Integer page, Integer size);

  byte[] getDownloadUser(String id);

  List<User> getAllUserDetails(Integer page, Integer size);

  int getAllUsersCount();

}
