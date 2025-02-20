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
    @ManyToOne
    private BookDetails book;

    @CreationTimestamp
    private LocalDateTime issuedDate;

    private LocalDateTime dueDate;

    private boolean isReturned = false;

    @PrePersist
    public void setDueDate() {
        this.dueDate = this.issuedDate.plusDays(14);
    }

}
