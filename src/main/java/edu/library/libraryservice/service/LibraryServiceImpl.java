package edu.library.libraryservice.service;

import edu.library.libraryservice.business.LibraryBusiness;
import edu.library.libraryservice.dto.BookDetailsDto;
import edu.library.libraryservice.dto.UserDto;
import edu.library.libraryservice.exception.LimitExceedException;
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
    public BookDetailsDto saveDetails(BookDetailsDto details) {
        BookDetails dto = libraryBusiness.saveBookDetails(bookDetailsMapper.toEntity(details));
        return bookDetailsMapper.toDto(dto);
    }

    @Override
    public LibraryDetails issueBook(String userId, String bookId) throws Exception {
        UserDto user = userService.getUser(userId);
        LibraryDetails libraryDetails = null;
        if (user != null) {
            BookDetails book = bookValidation.bookExistOrNot(bookId);
            bookValidation.validateUserBookLimit(user.getUserId(), String.valueOf(book.getId()));
            libraryDetails = libraryBusiness.details(libraryDetailsMapper.toEntity(userId,book));
        }
        return libraryDetails;
    }

    @Override
    public List<BookDetailsDto> getAllBooks() {
        List<BookDetails> books = libraryBusiness.getAllBooks();
        return bookDetailsMapper.toDtoList(books);
    }

    @Override
    public UserDto userLogin(String userId, String password) {
        return userService.userLogin(userId, password);
    }
}
