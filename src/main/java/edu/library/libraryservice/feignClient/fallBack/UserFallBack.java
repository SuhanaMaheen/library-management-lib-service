package edu.library.libraryservice.feignClient.fallBack;

import edu.library.libraryservice.dto.UserDto;
import edu.library.libraryservice.feignClient.UserClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UserFallBack implements UserClient {

    @Override
    public ResponseEntity<UserDto> getUser(String userId) {
        UserDto fallbackUser = new UserDto();
        fallbackUser.setName("Unavailable");
        fallbackUser.setEmail("unknown@example.com");
        return new ResponseEntity<>(fallbackUser, HttpStatus.SERVICE_UNAVAILABLE);
    }
}
