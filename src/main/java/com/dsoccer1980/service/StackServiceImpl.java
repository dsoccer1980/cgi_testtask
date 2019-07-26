package com.dsoccer1980.service;

import com.dsoccer1980.dto.UserNumbersDto;
import com.dsoccer1980.model.User;
import com.dsoccer1980.repository.StackEntityRepositoryImpl;
import com.dsoccer1980.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class StackServiceImpl implements StackService {

    private final UserRepository userRepository;
    private final StackEntityRepositoryImpl stack;

    public StackServiceImpl(UserRepository userRepository, StackEntityRepositoryImpl stack) {
        this.userRepository = userRepository;
        this.stack = stack;
    }

    @Override
    public User getOrCreateUserByName(String name) {
        return userRepository.findByName(name).orElseGet(() -> userRepository.save(new User(name)));
    }

    @Override
    public UserNumbersDto pop(int userId) {
        User user = userRepository.getOne(userId);
        try {
            stack.pop(user);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new UserNumbersDto(user.getName(), user.getId(), stack.view(user));
    }

    @Override
    public UserNumbersDto push(int userId, String numberText) {
        User user = userRepository.getOne(userId);
        try {
            int number = Integer.parseInt(numberText);
            stack.push(user, number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return new UserNumbersDto(user.getName(), user.getId(), stack.view(user));
    }

    @Override
    public UserNumbersDto reset(int userId) {
        User user = userRepository.getOne(userId);
        stack.reset(user);

        return new UserNumbersDto(user.getName(), user.getId(), stack.view(user));
    }

    @Override
    public UserNumbersDto getDto(User user) {
        return new UserNumbersDto(user.getName(), user.getId(), stack.view(user));
    }


}
