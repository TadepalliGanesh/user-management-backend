package com.example.usermanagement.entity;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Document("USER_COLLECTION")
@Data
public class User {

  @Id
  public String id;

  public String userName;

  public String emailId;

  public String phoneNo;

  public String password;

  @CreatedDate
  public Date createddate;
}
