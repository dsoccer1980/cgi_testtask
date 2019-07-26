package com.dsoccer1980.service;

import com.dsoccer1980.dto.UserNumbersDto;
import com.dsoccer1980.model.User;

public interface StackService {
    User getOrCreateUserByName(String name);

    UserNumbersDto pop(int userId);

    UserNumbersDto push(int userId, String numberText);

    UserNumbersDto reset(int userId);

    UserNumbersDto getDto(User user);
}
