package com.stackroute.userservices.dto;

import lombok.Data;

@Data
public class UserDto {
    
    private String userName;
    private String password;
    private String email;
    private String qualification;
}
