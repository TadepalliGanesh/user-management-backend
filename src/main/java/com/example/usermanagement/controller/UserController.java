package com.example.usermanagement.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.usermanagement.entity.TotalUsers;
import com.example.usermanagement.entity.User;
import com.example.usermanagement.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {
  public String CONTENT_DISPOSITION_FORMAT = "attachment; filename= %s";


  @Autowired
  public UserService userService;

  @PostMapping("/user-details")
  public ResponseEntity<User> storeDetails(@RequestParam("userName") String userName,
      @RequestParam("emailId") String emailId, @RequestParam("phoneNumber") String phoneNumber,
      @RequestParam("password") String password) {
    return new ResponseEntity<>(
        userService.storeUserDetails(userName, emailId, phoneNumber, password), HttpStatus.OK);
  }

  @GetMapping("/get-all-user-details")
  public ResponseEntity<List<User>> getAllUserDetails(@RequestParam Integer page , @RequestParam Integer size){
    return new ResponseEntity<>(userService.getAllUserDetails(page, size), HttpStatus.OK);
  }

   @GetMapping("/get-user-details")
  public ResponseEntity<TotalUsers> getDetails(@RequestParam String searchBy, @RequestParam String searchText, @RequestParam Integer page,
      @RequestParam Integer size){
    TotalUsers totalUsers= new TotalUsers();
    totalUsers.setUsersList(userService.getDetails(searchBy,searchText, page , size));
    if(!searchText.isEmpty())
      totalUsers.setTotalUsersCount(userService.getDetails(searchBy,searchText, page , size).size());
    else
      totalUsers.setTotalUsersCount(userService.getAllUsersCount());
    return new ResponseEntity<>(totalUsers, HttpStatus.OK);
  }


  @GetMapping("/download-report")
  public ResponseEntity<byte[]> getDownloadUser(@RequestParam String id){
    HttpHeaders httpHeaders= new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_PDF);
    httpHeaders.setContentDispositionFormData(HttpHeaders.CONTENT_DISPOSITION, String.format(CONTENT_DISPOSITION_FORMAT, "UserReport"));
    return ResponseEntity.ok()
        .headers(httpHeaders)
        .body(userService.getDownloadUser(id));

  }
}
