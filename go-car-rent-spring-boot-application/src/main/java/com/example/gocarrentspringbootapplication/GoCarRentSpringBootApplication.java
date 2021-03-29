package com.example.gocarrentspringbootapplication;

import com.example.gocarrentspringbootapplication.components.FeedbackRate;
import com.example.gocarrentspringbootapplication.components.RentStatus;
import com.example.gocarrentspringbootapplication.models.*;
import com.example.gocarrentspringbootapplication.repositories.AnnouncementRepository;
import com.example.gocarrentspringbootapplication.repositories.FeedbackRepository;
import com.example.gocarrentspringbootapplication.repositories.UserDetailsRepository;
import com.example.gocarrentspringbootapplication.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class GoCarRentSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoCarRentSpringBootApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository,
                                        UserDetailsRepository userDetailsRepository,
                                        AnnouncementRepository announcementRepository,
                                        FeedbackRepository feedbackRepository)  {
        return args -> {
            userRepository.deleteAll();
            userDetailsRepository.deleteAll();
            announcementRepository.deleteAll();

            User user = new User(
                    "user1@email.com",
                    "password",
                    new UserDetails(
                            "name",
                            "surname",
                            "phone",
                            "img"
                    )
            );
            userRepository.save(user);

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

            announcementRepository.save(new Announcement(
               RentStatus.FREE,
               new AnnouncementDetails(
                       "title1",
                       new BigDecimal("12.33"),
                       Currency.getInstance("USD"),
                       TimeUnit.HOURS,
                       "vw",
                       "polo"
               ),
                user
            ));

            feedbackRepository.save(new Feedback(
                    "Good",
                    FeedbackRate.FOUR,
                    user,
                    user
            ));

            feedbackRepository.save(new Feedback(
                    "Good",
                    FeedbackRate.TWO,
                    user,
                    user
            ));
        };
    }
}
