package com.dsoccer1980.web;

import com.dsoccer1980.model.User;
import com.dsoccer1980.repository.StackImpl;
import com.dsoccer1980.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final UserRepository userRepository;
    private final StackImpl stack;

    public MainController(UserRepository userRepository, StackImpl stack) {
        this.userRepository = userRepository;
        this.stack = stack;
    }


    @GetMapping("/")
    public String show() {
        return "index";
    }

    @GetMapping("/accept")
    public String accept(@RequestParam(value = "name") String name, Model model) {
        User user = userRepository.findByName(name).orElseGet(() -> userRepository.save(new User(name)));
        List<Integer> numbers = stack.view(user);

        model.addAttribute("user_name", user.getName());
        model.addAttribute("user_id", user.getId());
        model.addAttribute("numbers", numbers);
        return "view";
    }


}