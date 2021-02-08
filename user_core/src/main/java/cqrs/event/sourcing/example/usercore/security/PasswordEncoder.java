package cqrs.event.sourcing.example.usercore.security;

public interface PasswordEncoder {
    String hashPassword(String password);
}
