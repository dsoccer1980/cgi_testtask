package com.dsoccer1980.repository;

import com.dsoccer1980.model.Stack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JpaStackEntityRepository extends JpaRepository<Stack, Integer> {

    @Query("Select Max(s.id) From Stack s Where s.user.id=:userId")
    Optional<Integer> getMaxId(@Param("userId") int userId);

    void deleteByUserId(int userId);

    List<Stack> findByUserId(int userId);


}
