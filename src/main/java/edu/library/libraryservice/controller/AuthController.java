package edu.library.libraryservice.controller;

import edu.library.libraryservice.dto.LibraryResponse;
import edu.library.libraryservice.dto.LoginRequest;
import edu.library.libraryservice.dto.UserDto;
import edu.library.libraryservice.service.LibraryService;
import edu.library.libraryservice.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private LibraryService libraryService;
    @Autowired
    private JwtUtil jwtUtil;

    @Operation(summary = "Login", description = "User Login.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Login Successfull"),
            @ApiResponse(responseCode = "400", description = "Bad request - Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/login")
    public ResponseEntity<LibraryResponse<String>> login(@RequestBody LoginRequest request) {
        String token = "";
        try {
            String userId = request.getUserId();
            String password = request.getPassword();
            UserDto userDto = libraryService.userLogin(userId, password);
            if (userDto != null) {
                token = jwtUtil.generateToken(userDto.getUserId());
            }
            System.out.println(token);
            return ResponseEntity.ok(new LibraryResponse<>(HttpStatus.OK.name(), "Token Created", "token :" + token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new LibraryResponse<>(HttpStatus.NOT_FOUND.name(), e.getMessage(), null));
        }
    }
}
