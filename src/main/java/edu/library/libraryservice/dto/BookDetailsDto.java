package edu.library.libraryservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BookDetailsDto {
    @JsonIgnore
    private long id;
    @NotBlank
    @NotEmpty(message = "Author cannot be empty")
    private String author;
    @NotBlank
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotBlank
    @NotEmpty(message = "Category cannot be empty")
    private String category;
    private String description;
}
