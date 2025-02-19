package edu.library.libraryservice.validation;

import edu.library.libraryservice.model.BookDetails;
import edu.library.libraryservice.model.LibraryDetails;
import edu.library.libraryservice.repository.BookDetailsRepository;
import edu.library.libraryservice.repository.LibraryDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidateBook {
    @Autowired
    private BookDetailsRepository bookDetailsRepository;
    @Autowired
    private LibraryDetailsRepository libraryDetailsRepository;

    public BookDetails validateBookExist(String bookId) {
        BookDetails details = bookDetailsRepository.findById(Long.valueOf(bookId)).orElseThrow(
                () -> new RuntimeException("Book not found"));
        return details;
    }

    public List<LibraryDetails> validateUserBookLimit(String userId,String bookId) throws Exception {
        List<LibraryDetails> details = libraryDetailsRepository.findUnreturnedBooksByUserId(userId);
        if (details.stream().anyMatch(detail -> detail.getBookId().equals(bookId))) {
            throw new Exception("This book is already assigned to the user.");
        }if(details.size()>=3) {
            throw new Exception("Cannot Assign ..Already this user has 3 books");
        }return details;
    }
}
