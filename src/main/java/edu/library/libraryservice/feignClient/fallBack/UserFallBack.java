package edu.library.libraryservice.feignClient.fallBack;

import edu.library.libraryservice.dto.UserDto;
import edu.library.libraryservice.dto.UserResponse;
import edu.library.libraryservice.feignClient.UserClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserFallBack implements UserClient {

    @Override
    public ResponseEntity<UserResponse<UserDto>> getUser(String userId) {
        UserResponse<UserDto> fallbackUser = null;
        return new ResponseEntity<>(fallbackUser, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @Override
    public ResponseEntity<UserResponse<UserDto>> userLogin(String userId, String password) {
        UserResponse<UserDto> fallbackUser = null;
        return new ResponseEntity<>(fallbackUser, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
