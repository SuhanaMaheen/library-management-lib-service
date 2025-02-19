package edu.library.libraryservice.service;

import edu.library.libraryservice.business.LibraryBusiness;
import edu.library.libraryservice.dto.BookDetailsDto;
import edu.library.libraryservice.mapper.BookDetailsMapper;
import edu.library.libraryservice.mapper.LibraryDetailsMapper;
import edu.library.libraryservice.model.BookDetails;
import edu.library.libraryservice.model.LibraryDetails;
import edu.library.libraryservice.validation.ValidateBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    @Autowired
    private LibraryBusiness libraryBusiness;
    @Autowired
    private BookDetailsMapper bookDetailsMapper;
    @Autowired
    private LibraryDetailsMapper libraryDetailsMapper;
    @Autowired
    private ValidateBook bookValidation;
    @Autowired
    private UserService userService;


    @Override
    public BookDetails saveDetails(BookDetailsDto details) {
        return libraryBusiness.saveBookDetails(bookDetailsMapper.toEntity(details));
    }
    @Override
    public LibraryDetails issueBook(String userId,String bookId) throws Exception {
        bookValidation.validateBookExist(bookId);
        userService.getUser(userId);
        bookValidation.validateUserBookLimit(userId,bookId);
        return libraryBusiness.details(libraryDetailsMapper.toEntity(userId,bookId));
    }

    @Override
    public List<BookDetailsDto> getAllBooks()  {
        List<BookDetails> books = libraryBusiness.getAllBooks();
        return bookDetailsMapper.toDtoList(books);
    }
}
