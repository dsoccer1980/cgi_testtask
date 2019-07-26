package com.dsoccer1980.repository;

import com.dsoccer1980.model.StackEntity;
import com.dsoccer1980.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@ComponentScan({"com.dsoccer1980.repository"})
class StackEntityRepositoryImplTest {

    private final User USER1 = new User("TestUser1");
    private final User USER2 = new User("TestUser2");
    @Autowired
    private JpaStackEntityRepository jpaStackEntityRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StackEntityRepository stackEntityRepository;

    @BeforeEach
    void before() {
        userRepository.save(USER1);
        userRepository.save(USER2);
        jpaStackEntityRepository.deleteAll();
        jpaStackEntityRepository.save(new StackEntity(7, USER1));
        jpaStackEntityRepository.save(new StackEntity(3, USER1));
        jpaStackEntityRepository.save(new StackEntity(2, USER1));
        jpaStackEntityRepository.save(new StackEntity(99, USER2));
    }

    @Test
    void viewForUser1() {
        assertThat(stackEntityRepository.view(USER1)).isEqualTo(Arrays.asList(7, 3, 2));
    }

    @Test
    void viewForUser2() {
        assertThat(stackEntityRepository.view(USER2)).isEqualTo(Collections.singletonList(99));
    }

    @Test
    void pushForUser1() {
        stackEntityRepository.push(USER1, 10);
        assertThat(stackEntityRepository.view(USER1)).isEqualTo(Arrays.asList(7, 3, 2, 10));
    }

    @Test
    void pushForUser2() {
        stackEntityRepository.push(USER2, 11);
        assertThat(stackEntityRepository.view(USER2)).isEqualTo(Arrays.asList(99, 11));
    }

    @Test
    void popForUser1() {
        stackEntityRepository.pop(USER1);
        assertThat(stackEntityRepository.view(USER1)).isEqualTo(Arrays.asList(7, 3));
    }

    @Test
    void popForUser2() {
        stackEntityRepository.pop(USER2);
        assertThat(stackEntityRepository.view(USER2)).isEqualTo(Collections.emptyList());
    }

    @Test
    void resetForUser1() {
        stackEntityRepository.reset(USER1);
        assertThat(stackEntityRepository.view(USER1)).isEqualTo(Collections.emptyList());
    }

    @Test
    void resetForUser2() {
        stackEntityRepository.reset(USER2);
        assertThat(stackEntityRepository.view(USER2)).isEqualTo(Collections.emptyList());
    }

}