package edu.library.libraryservice.dto;

import lombok.Data;

@Data
public class UserResponse<T> {

    private String status;
    private String message;
    private T data;

    public UserResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
