package com.dsoccer1980.repository;

import com.dsoccer1980.model.User;

import java.util.List;
import java.util.NoSuchElementException;

public interface StackEntityRepository {

    void push(User user, int e);

    int pop(User user) throws NoSuchElementException;

    void reset(User user);

    List<Integer> view(User user);
}
