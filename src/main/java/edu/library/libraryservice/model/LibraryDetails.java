package edu.library.libraryservice.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "library_details")
public class LibraryDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String bookId;

    @CreationTimestamp
    private LocalDateTime issuedDate;

    private boolean isReturned = false;

}
