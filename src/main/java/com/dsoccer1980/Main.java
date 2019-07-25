package com.dsoccer1980;

import com.dsoccer1980.model.User;
import com.dsoccer1980.repository.StackImpl;
import com.dsoccer1980.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {


        ConfigurableApplicationContext context = SpringApplication.run(Main.class);
        UserRepository userRepository = context.getBean(UserRepository.class);
        userRepository.save(new User("Denis"));
        User user = userRepository.findById(1).get();

        StackImpl stack = context.getBean(StackImpl.class);

        stack.reset(user);
        stack.push(user, 7);
        stack.push(user, 1);
        stack.push(user, 3);
        System.out.println(stack.view(user));

        System.out.println(stack.pop(user));
        System.out.println(stack.pop(user));

        System.out.println(stack.view(user));


    }
}
