package edu.library.libraryservice.dto;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String name;
    private String email;
    private String phoneNumber;
}

