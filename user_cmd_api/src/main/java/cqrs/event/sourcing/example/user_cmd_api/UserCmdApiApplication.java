package cqrs.event.sourcing.example.user_cmd_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserCmdApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCmdApiApplication.class, args);
    }
}
