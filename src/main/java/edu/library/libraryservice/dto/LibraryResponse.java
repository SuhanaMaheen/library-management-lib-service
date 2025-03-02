package edu.library.libraryservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryResponse<T> {

    private String status;
    private String message;
    private T data;

    public LibraryResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

}
