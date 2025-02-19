package edu.library.libraryservice.feignClient;

import edu.library.libraryservice.dto.UserDto;
import edu.library.libraryservice.feignClient.fallBack.UserFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "user-service", url = "http://localhost:8081/api/users",fallback = UserFallBack.class)
public interface UserClient {

    @GetMapping("/getUser")
    ResponseEntity<UserDto> getUser(@RequestParam("userId") String userId);
}

