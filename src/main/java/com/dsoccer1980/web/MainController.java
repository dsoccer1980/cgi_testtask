package com.dsoccer1980.web;

import com.dsoccer1980.dto.Dto;
import com.dsoccer1980.model.User;
import com.dsoccer1980.service.StackService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private final StackService stackService;

    public MainController(StackService stackService) {
        this.stackService = stackService;
    }

    @GetMapping({"/", "/logout"})
    public String show() {
        return "index";
    }

    @GetMapping("/accept")
    public String accept(@RequestParam(value = "name") String name, Model model) {
        User user = stackService.getOrCreateUserByName(name);
        return returnView(model, stackService.getDto(user));
    }

    @GetMapping("/pop")
    public String pop(@RequestParam(value = "user_id") int userId, Model model) {
        Dto dto = stackService.pop(userId);
        return returnView(model, dto);
    }

    @GetMapping("/push")
    public String push(@RequestParam(value = "user_id") int userId, @RequestParam(value = "number") String numberText, Model model) {
        Dto dto = stackService.push(userId, numberText);
        return returnView(model, dto);
    }

    @GetMapping("/reset")
    public String reset(@RequestParam(value = "user_id") int userId, Model model) {
        Dto dto = stackService.reset(userId);
        return returnView(model, dto);
    }

    private String returnView(Model model, Dto dto) {
        model.addAttribute("user_name", dto.getUserName());
        model.addAttribute("user_id", dto.getUserId());
        model.addAttribute("numbers", dto.getNumbers());
        return "view";
    }


}