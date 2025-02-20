package edu.library.libraryservice.business;

import edu.library.libraryservice.model.BookDetails;
import edu.library.libraryservice.model.LibraryDetails;
import edu.library.libraryservice.repository.BookDetailsRepository;
import edu.library.libraryservice.repository.LibraryDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LibraryBusinessImpl implements LibraryBusiness {
    @Autowired
    private BookDetailsRepository bookDetailsRepository;

    @Autowired
    private LibraryDetailsRepository libraryRepository;

    @Override
    public BookDetails saveBookDetails(BookDetails details) {
        return bookDetailsRepository.save(details);
    }

    @Override
    public LibraryDetails details(LibraryDetails details) {
        return libraryRepository.save(details);
    }

    @Override
    public List<BookDetails> getAllBooks() {
        return bookDetailsRepository.findAll();
    }
}
