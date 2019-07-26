package com.dsoccer1980.web;

import com.dsoccer1980.model.User;
import com.dsoccer1980.repository.StackEntityRepositoryImpl;
import com.dsoccer1980.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final StackEntityRepositoryImpl stack;

    public MainController(UserRepository userRepository, StackEntityRepositoryImpl stack) {
        this.userRepository = userRepository;
        this.stack = stack;
    }

    @GetMapping({"/", "/logout"})
    public String show() {
        return "index";
    }

    @GetMapping("/accept")
    public String accept(@RequestParam(value = "name") String name, Model model) {
        User user = userRepository.findByName(name).orElseGet(() -> userRepository.save(new User(name)));
        return returnView(model, user);
    }

    @GetMapping("/pop")
    public String pop(@RequestParam(value = "user_id") int userId, Model model) {
        User user = userRepository.getOne(userId);
        try {
            stack.pop(user);
        } catch (NoSuchElementException e) {
            user = userRepository.getOne(userId);
            model.addAttribute("stack_empty", "true");
        }

        return returnView(model, user);
    }

    @GetMapping("/push")
    public String push(@RequestParam(value = "user_id") int userId, @RequestParam(value = "number") int number, Model model) {
        User user = userRepository.getOne(userId);
        stack.push(user, number);

        return returnView(model, user);
    }

    @GetMapping("/reset")
    public String rest(@RequestParam(value = "user_id") int userId, Model model) {
        User user = userRepository.getOne(userId);
        stack.reset(user);

        return returnView(model, user);
    }

    private String returnView(Model model, User user) {
        List<Integer> numbers = stack.view(user);
        model.addAttribute("user_name", user.getName());
        model.addAttribute("user_id", user.getId());
        model.addAttribute("numbers", numbers);
        return "view";
    }


}