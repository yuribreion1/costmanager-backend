package com.finance.costmanager.repository;

import com.finance.costmanager.model.User;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
public class UserRepositoryTest {

    public static User createUserDummy() {
        return User.builder().name("Yuri Breion").email("yuribreion@gmail.com").password("1234").build();
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    void verifyIfEmailExists() {
        //scenario
        User user = createUserDummy();
        userRepository.save(user);
        //action
        boolean result = userRepository.existsByEmail("yuribreion@gmail.com");
        //verification - output
        Assertions.assertThat(result).isTrue();
    }

    @Test
    void verifyIfUserNotExists() {
        // action
        boolean result = userRepository.existsByEmail("yuribreion@gmail.com");
        // verification
        Assertions.assertThat(result).isFalse();
    }

    @Test
    void verifyIfUserExists() {
        User user = createUserDummy();
        //action
        User userSaved = userRepository.save(user);
        Assertions.assertThat(userSaved.getId()).isNotNull();
    }

    @Test
    void findUserByUsernameAndEmail() {

        User user = createUserDummy();
        userRepository.save(user);

        Optional<User> result = userRepository.findByEmail("yuribreion@gmail.com");
        Assertions.assertThat(result.isPresent()).isTrue();

    }
}
