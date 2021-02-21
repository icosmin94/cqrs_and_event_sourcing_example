package com.springbank.oauth2_server.services;

import com.springbank.oauth2_server.repositories.UserRepository;
import cqrs.event.sourcing.example.usercore.models.Account;
import cqrs.event.sourcing.example.usercore.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user =  userRepository.findByUsername(username);
        if (user.isPresent()) {
            Account account = user.get().getAccount();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(username)
                    .password(account.getPassword())
                    .authorities(account.getRoles())
                    .accountExpired(false)
                    .accountLocked(false)
                    .credentialsExpired(false)
                    .disabled(false)
                    .build();
        } else {
            throw new UsernameNotFoundException("Incorrect Username/Password supplied");
        }
    }
}
