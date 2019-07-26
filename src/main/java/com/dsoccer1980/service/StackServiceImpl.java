package com.dsoccer1980.service;

import com.dsoccer1980.dto.Dto;
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
    public Dto pop(int userId) {
        User user = userRepository.getOne(userId);
        try {
            stack.pop(user);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return new Dto(user.getName(), user.getId(), stack.view(user));
    }

    @Override
    public Dto push(int userId, String numberText) {
        User user = userRepository.getOne(userId);
        try {
            int number = Integer.parseInt(numberText);
            stack.push(user, number);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return new Dto(user.getName(), user.getId(), stack.view(user));
    }

    @Override
    public Dto reset(int userId) {
        User user = userRepository.getOne(userId);
        stack.reset(user);

        return new Dto(user.getName(), user.getId(), stack.view(user));
    }

    @Override
    public Dto getDto(User user) {
        return new Dto(user.getName(), user.getId(), stack.view(user));
    }


}
