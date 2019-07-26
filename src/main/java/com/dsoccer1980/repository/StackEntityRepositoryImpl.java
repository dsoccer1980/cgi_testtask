package com.dsoccer1980.repository;

import com.dsoccer1980.model.Stack;
import com.dsoccer1980.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
@Transactional
public class StackEntityRepositoryImpl implements StackEntityRepository {

    private final JpaStackEntityRepository stackEntityRepository;

    public StackEntityRepositoryImpl(JpaStackEntityRepository stackEntityRepository) {
        this.stackEntityRepository = stackEntityRepository;
    }

    @Override
    public void push(User user, int e) {
        stackEntityRepository.save(new Stack(e, user));
    }

    @Override
    public int pop(User user) throws NoSuchElementException {
        int maxId = stackEntityRepository.getMaxId(user.getId()).orElseThrow(NoSuchElementException::new);
        Stack stack = stackEntityRepository.findById(maxId).orElseThrow(NoSuchElementException::new);
        stackEntityRepository.deleteById(maxId);
        return stack.getNumber();
    }

    @Override
    public void reset(User user) {
        stackEntityRepository.deleteByUserId(user.getId());
    }

    @Override
    public List<Integer> view(User user) {
        return stackEntityRepository
                .findByUserId(user.getId())
                .stream()
                .sorted(Comparator.comparingInt(Stack::getId).reversed())
                .map(Stack::getNumber)
                .collect(Collectors.toList());
    }
}
