package com.finance.costmanager.service;

import com.finance.costmanager.model.User;
import com.finance.costmanager.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Test
    void validateEmailTest() {
        //scenario
        userRepository.deleteAll();
        //action
         userService.validateEmail("yuribreion@gmail.com");
    }

    @Test
    void CheckIfUserExistsException() {
        User user = User.builder()
                .name("Yuri Breion")
                .email("yuribreion@gmail.com")
                .build();

        userRepository.save(user);

        userService.validateEmail(user.getEmail());
    }
}
