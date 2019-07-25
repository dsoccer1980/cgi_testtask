package com.dsoccer1980.repository;

import com.dsoccer1980.model.StackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StackEntityRepository extends JpaRepository<StackEntity, Integer> {

    @Query("Select Max(s.id) From StackEntity s Where s.user.id=:userId")
    Optional<Integer> getMaxId(@Param("userId") int userId);

    void deleteByUserId(int userId);


}
