package edu.library.libraryservice.mapper;

import edu.library.libraryservice.model.LibraryDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class LibraryDetailsMapper {

    public LibraryDetails toEntity(String userId, String bookId) {

        LibraryDetails entity = new LibraryDetails();
        entity.setUserId(userId);
        entity.setBookId(bookId);
        entity.setIssuedDate(LocalDateTime.now());
        return entity;
    }
}
