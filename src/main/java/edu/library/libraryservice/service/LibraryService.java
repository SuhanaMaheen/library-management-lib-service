package edu.library.libraryservice.service;

import edu.library.libraryservice.dto.BookDetailsDto;
import edu.library.libraryservice.model.BookDetails;
import edu.library.libraryservice.model.LibraryDetails;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LibraryService {
    BookDetails saveDetails(BookDetailsDto details);
    LibraryDetails issueBook(String userId,String bookId) throws Exception;
    List<BookDetailsDto> getAllBooks() ;
}
