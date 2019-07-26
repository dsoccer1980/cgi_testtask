package com.dsoccer1980.web;

import com.dsoccer1980.dto.UserNumbersDto;
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
        UserNumbersDto userNumbersDto = stackService.pop(userId);
        return returnView(model, userNumbersDto);
    }

    @GetMapping("/push")
    public String push(@RequestParam(value = "user_id") int userId, @RequestParam(value = "number") String numberText, Model model) {
        UserNumbersDto userNumbersDto = stackService.push(userId, numberText);
        return returnView(model, userNumbersDto);
    }

    @GetMapping("/reset")
    public String reset(@RequestParam(value = "user_id") int userId, Model model) {
        UserNumbersDto userNumbersDto = stackService.reset(userId);
        return returnView(model, userNumbersDto);
    }

    private String returnView(Model model, UserNumbersDto userNumbersDto) {
        model.addAttribute("user_name", userNumbersDto.getUserName());
        model.addAttribute("user_id", userNumbersDto.getUserId());
        model.addAttribute("numbers", userNumbersDto.getNumbers());
        return "view";
    }


}