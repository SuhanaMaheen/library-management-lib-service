package edu.library.libraryservice.controller;

import edu.library.libraryservice.dto.BookDetailsDto;
import edu.library.libraryservice.dto.LibraryResponse;
import edu.library.libraryservice.dto.LoginRequest;
import edu.library.libraryservice.service.LibraryService;
import edu.library.libraryservice.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/api/library")
@SecurityRequirement(name = "bearerAuth") // Requires authentication for all methods in this controller
public class LibraryController {
    @Autowired
    private LibraryService libraryService;

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public LibraryController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping(value = "/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtUtil.generateToken(userDetails.getUsername());

        return Map.of("token", token);
    }

    @Operation(summary = "Save Book Details", description = "Store book details in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Details saved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/saveBookDetails")
    public ResponseEntity<String> saveBookDetails(
            @Valid @RequestBody BookDetailsDto request
            ) throws Exception {
        try {
          libraryService.saveDetails(request);
            return ResponseEntity.ok("Saved successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @Operation(summary = "Save Book Details", description = "Store book details in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Details saved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/issueBook")
    public ResponseEntity<String> issueBook(
            @Parameter(description = "UserId")
            @RequestParam(value = "userId",required = false) String userId,
            @Parameter(description = "bookId")
            @RequestParam(value = "bookId",required = false) String bookId
    ) throws Exception {
        try {
            libraryService.issueBook(userId, bookId);
            return ResponseEntity.ok("Saved successfully");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<LibraryResponse<List<BookDetailsDto>>> getAll()  {
        try {
            List<BookDetailsDto> bookList = libraryService.getAllBooks();
            return ResponseEntity.ok(new LibraryResponse<>(HttpStatus.OK.name(), "Data Fetched successfully", bookList));
        } catch (Exception e) {
            return ResponseEntity.ok(new LibraryResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.name(), e.getMessage(), null));
        }
    }

}
