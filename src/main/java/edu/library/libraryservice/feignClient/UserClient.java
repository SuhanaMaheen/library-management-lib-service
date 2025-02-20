package edu.library.libraryservice.feignClient;

import edu.library.libraryservice.dto.UserDto;
import edu.library.libraryservice.dto.UserResponse;
import edu.library.libraryservice.feignClient.fallBack.UserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "${user.service.url}", fallback = UserFallBack.class)
public interface UserClient {

    @GetMapping("/getUser")
    ResponseEntity<UserResponse<UserDto>> getUser(@RequestParam("userId") String userId);

    @PostMapping("/login")
    ResponseEntity<UserResponse<UserDto>> userLogin(@RequestParam("userId") String userId,
                                                    @RequestParam("password") String password);
}

