package edu.library.libraryservice.business;

import edu.library.libraryservice.dto.BookDetailsDto;
import edu.library.libraryservice.model.BookDetails;
import edu.library.libraryservice.model.LibraryDetails;

import java.util.List;

public interface LibraryBusiness {
    BookDetails saveBookDetails(BookDetails details);
    LibraryDetails details(LibraryDetails details);
    List<BookDetails> getAllBooks() ;


}
