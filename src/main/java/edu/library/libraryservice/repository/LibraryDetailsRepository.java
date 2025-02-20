package edu.library.libraryservice.repository;

import edu.library.libraryservice.dto.BookDetailsDto;
import edu.library.libraryservice.model.BookDetails;
import edu.library.libraryservice.model.LibraryDetails;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LibraryDetailsRepository extends JpaRepository<LibraryDetails, Long> {
    @Query("SELECT l FROM LibraryDetails l WHERE l.userId = :userId AND l.isReturned = false")
    List<LibraryDetails> findUnreturnedBooksByUserId(@Param("userId") String userId);

    List<LibraryDetails> findByDueDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}


