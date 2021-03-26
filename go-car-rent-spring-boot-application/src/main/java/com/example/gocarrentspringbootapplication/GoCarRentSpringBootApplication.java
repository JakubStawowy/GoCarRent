package com.example.gocarrentspringbootapplication;

import com.example.gocarrentspringbootapplication.models.User;
import com.example.gocarrentspringbootapplication.models.UserDetails;
import com.example.gocarrentspringbootapplication.repositories.UserDetailsRepository;
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
    CommandLineRunner commandLineRunner(UserRepository userRepository, UserDetailsRepository userDetailsRepository)  {
        return args -> {
            userRepository.deleteAll();
            userDetailsRepository.deleteAll();

            userRepository.save(new User(
                    "user1@email.com",
                    "password",
                    new UserDetails(
                            "name",
                            "surname",
                            "phone",
                            "img"
                    )
            ));

            userRepository.save(new User(
                    "user2@email.com",
                    "password",
                    new UserDetails(
                            "name2",
                            "surname2",
                            "phone2",
                            "img2"
                    )
            ));
        };
    }
}
