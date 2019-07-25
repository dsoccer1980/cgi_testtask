package com.dsoccer1980.repository;

import com.dsoccer1980.model.StackEntity;
import com.dsoccer1980.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
@Transactional
public class StackImpl implements Stack {

    private final StackEntityRepository stackEntityRepository;
    private final UserRepository userRepository;

    public StackImpl(StackEntityRepository stackEntityRepository, UserRepository userRepository) {
        this.stackEntityRepository = stackEntityRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void push(User user, int e) {
        stackEntityRepository.save(new StackEntity(e, user));
    }

    @Override
    public int pop(User user) throws NoSuchElementException {
        int maxId = stackEntityRepository.getMaxId(user.getId()).orElseThrow(NoSuchElementException::new);
        StackEntity stackEntity = stackEntityRepository.findById(maxId).orElseThrow(NoSuchElementException::new);
        stackEntityRepository.deleteById(maxId);
        return stackEntity.getNumber();
    }

    @Override
    public void reset(User user) {
        stackEntityRepository.deleteByUserId(user.getId());

    }

    @Override
    public List<Integer> view(User user) {
        return stackEntityRepository.findAll().stream().map(StackEntity::getNumber).collect(Collectors.toList());
    }
}