package edu.library.libraryservice.mapper;

import edu.library.libraryservice.model.BookDetails;
import edu.library.libraryservice.model.LibraryDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class LibraryDetailsMapper {

    public LibraryDetails toEntity(String userId, BookDetails book) {
        LibraryDetails entity = new LibraryDetails();
        entity.setUserId(userId);
        entity.setBook(book);
        entity.setIssuedDate(LocalDateTime.now());
        return entity;
    }
}
