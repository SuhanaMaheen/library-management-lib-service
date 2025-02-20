package edu.library.libraryservice.service;

import edu.library.libraryservice.dto.UserDto;
import edu.library.libraryservice.dto.UserResponse;
import edu.library.libraryservice.feignClient.UserClient;
import edu.library.libraryservice.model.BookDetails;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserClient userClient;

    public UserDto getUser(String userId) {
        UserResponse<UserDto> userDto = userClient.getUser(userId).getBody();
        System.out.println("userDto = " + userDto.getData());
        return userDto.getData();
    }
    public UserDto userLogin(String userId, String password) {
        UserResponse<UserDto> userDto = userClient.userLogin(userId,password).getBody();
        System.out.println("userDto = " + userDto.getData());
        return userDto.getData();
    }
}
