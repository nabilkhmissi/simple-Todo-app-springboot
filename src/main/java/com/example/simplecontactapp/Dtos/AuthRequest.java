package com.example.simplecontactapp.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequest {
   private String email;
   private String password;
}
