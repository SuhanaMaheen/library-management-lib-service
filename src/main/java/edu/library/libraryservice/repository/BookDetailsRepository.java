package edu.library.libraryservice.repository;

import edu.library.libraryservice.model.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDetailsRepository extends JpaRepository<BookDetails, Long> {
}
