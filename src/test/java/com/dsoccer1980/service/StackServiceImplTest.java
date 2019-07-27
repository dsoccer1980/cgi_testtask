package com.dsoccer1980.service;

import com.dsoccer1980.dto.UserNumbersDto;
import com.dsoccer1980.model.Stack;
import com.dsoccer1980.model.User;
import com.dsoccer1980.repository.JpaStackEntityRepository;
import com.dsoccer1980.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
class StackServiceImplTest {

    private final User USER1 = new User("TestUser1");
    @Autowired
    private StackService stackService;
    @Autowired
    private JpaStackEntityRepository jpaStackEntityRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void before() {
        userRepository.deleteAll();
        userRepository.save(USER1);
        jpaStackEntityRepository.deleteAll();
        jpaStackEntityRepository.save(new Stack(7, USER1));
        jpaStackEntityRepository.save(new Stack(3, USER1));
        jpaStackEntityRepository.save(new Stack(2, USER1));
    }

    @Test
    void getOrCreateUserByName() {
        User testUser = stackService.getOrCreateUserByName(USER1.getName());
        assertThat(testUser).isEqualTo(USER1);
    }

    @Test
    void pop() {
        UserNumbersDto dto = stackService.pop(USER1.getId());
        assertThat(dto).isEqualTo(new UserNumbersDto(USER1.getName(), USER1.getId(), Arrays.asList(3, 7)));
    }

    @Test
    void push() {
        UserNumbersDto dto = stackService.push(USER1.getId(), "8");
        assertThat(dto).isEqualTo(new UserNumbersDto(USER1.getName(), USER1.getId(), Arrays.asList(8, 2, 3, 7)));
    }

    @Test
    void reset() {
        UserNumbersDto dto = stackService.reset(USER1.getId());
        assertThat(dto).isEqualTo(new UserNumbersDto(USER1.getName(), USER1.getId(), Collections.emptyList()));
    }

    @Test
    void getDto() {
        UserNumbersDto dto = stackService.getDto(USER1);
        assertThat(dto).isEqualTo(new UserNumbersDto(USER1.getName(), USER1.getId(), Arrays.asList(2, 3, 7)));
    }
}