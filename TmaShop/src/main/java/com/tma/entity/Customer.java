package com.tma.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
private int customerId ;
private String name  ;
private String email;
private String phone ;
private String password;
private Date registeredDate;
private short status;
}
