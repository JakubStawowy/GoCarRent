package com.example.gocarrentspringbootapplication;

import com.example.gocarrentspringbootapplication.models.User;
import com.example.gocarrentspringbootapplication.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GoCarRentSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoCarRentSpringBootApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository)  {
        return args -> {
            repository.save(new User(
                    "user1@email.com",
                    "password"
            ));

            repository.save(new User(
                    "user2@email.com",
                    "password"
            ));
        };
    }
}
