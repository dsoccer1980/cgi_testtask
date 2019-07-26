package com.dsoccer1980.service;

import com.dsoccer1980.dto.Dto;
import com.dsoccer1980.model.User;

public interface StackService {
    User getOrCreateUserByName(String name);

    Dto pop(int userId);

    Dto push(int userId, String numberText);

    Dto reset(int userId);

    Dto getDto(User user);
}
