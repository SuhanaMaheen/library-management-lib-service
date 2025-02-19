package edu.library.libraryservice.service;

import edu.library.libraryservice.dto.UserDto;
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
        UserDto userDto = userClient.getUser(userId).getBody();
        System.out.println("userDto = " + userDto);
        return userDto;
    }
}
