package edu.library.libraryservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LibraryResponse<T>{

    private String status;
    private String message;
    private T data;

    public LibraryResponse(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
