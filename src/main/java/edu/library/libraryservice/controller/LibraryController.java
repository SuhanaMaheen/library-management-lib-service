package edu.library.libraryservice.controller;

import edu.library.libraryservice.dto.BookDetailsDto;
import edu.library.libraryservice.dto.LibraryResponse;
import edu.library.libraryservice.exception.LimitExceedException;
import edu.library.libraryservice.model.LibraryDetails;
import edu.library.libraryservice.service.LibraryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@SecurityRequirement(name = "bearerAuth")
public class LibraryController {
    @Autowired
    private LibraryService libraryService;


    @Operation(summary = "Save Book Details", description = "Store book details in the system.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Details saved successfully"), @ApiResponse(responseCode = "400", description = "Bad request - Invalid input data"), @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(value = "/createBook")
    public ResponseEntity<LibraryResponse<BookDetailsDto>> saveBookDetails(@RequestBody BookDetailsDto request) throws Exception {
        try {
            BookDetailsDto bookDetailsDto = libraryService.saveDetails(request);
            return ResponseEntity.ok(new LibraryResponse<>(HttpStatus.OK.name(), "Book Created Successfully", bookDetailsDto));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new LibraryResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage()));
        }
    }

    @Operation(summary = "Issue Book to User", description = "Assigning book to User in the system.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Book Details Fetched Successfully"), @ApiResponse(responseCode = "400", description = "Bad request - Invalid input data"), @ApiResponse(responseCode = "500", description = "Internal server error")})
    @PostMapping(value = "/issueBook")
    public ResponseEntity<LibraryResponse<String>> issueBook(@Parameter(description = "UserId") @RequestParam(value = "userId", required = false) String userId,
                                                             @Parameter(description = "bookId") @RequestParam(value = "bookId", required = false) String bookId) throws Exception {
        try {
            LibraryDetails libraryDetails = libraryService.issueBook(userId, bookId);
            return ResponseEntity.ok(new LibraryResponse<>(HttpStatus.OK.name(), "Book Assigned Successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new LibraryResponse<>(HttpStatus.FORBIDDEN.name(), e.getMessage()));
        }
    }

    @GetMapping(value = "/getAllBooks")
    public ResponseEntity<LibraryResponse<List<BookDetailsDto>>> getAll() {
        try {
            List<BookDetailsDto> bookList = libraryService.getAllBooks();
            return ResponseEntity.ok(new LibraryResponse<>(HttpStatus.OK.name(), "Data Retrieved Successfully", bookList));
        } catch (Exception e) {
            return ResponseEntity.ok(new LibraryResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage()));
        }
    }

}
