package edu.library.libraryservice.validation;

import edu.library.libraryservice.exception.LimitExceedException;
import edu.library.libraryservice.model.BookDetails;
import edu.library.libraryservice.model.LibraryDetails;
import edu.library.libraryservice.repository.BookDetailsRepository;
import edu.library.libraryservice.repository.LibraryDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidateBook {
    @Autowired
    private BookDetailsRepository bookDetailsRepository;
    @Autowired
    private LibraryDetailsRepository libraryDetailsRepository;

    public BookDetails bookExistOrNot(String bookId) {
        BookDetails details = bookDetailsRepository.findById(Long.valueOf(bookId)).orElseThrow(
                () -> new RuntimeException("Book not found"));
        return details;
    }

    public List<LibraryDetails> validateUserBookLimit(String userId,String bookId) throws Exception {
        List<LibraryDetails> details = libraryDetailsRepository.findUnreturnedBooksByUserId(userId);
        if (details.stream().anyMatch(detail -> detail.getBook() != null && detail.getBook().getId().equals(Long.valueOf(bookId)))) {
            throw new Exception("This book is already assigned to this user.");
        }if(details.size()>=3) {
            throw new LimitExceedException("Cannot Assign Book,User Currently Holding 3 Books");
        }return details;
    }
}
