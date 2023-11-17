package com.example.usermanagement.entity;

import java.util.List;


import lombok.Data;


@Data
public class TotalUsers {

  public List<User> usersList;

  public int totalUsersCount;
}
