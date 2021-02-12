package cqrs.event.sourcing.example.user_query_api.handlers;

import com.google.common.base.Preconditions;
import cqrs.event.sourcing.example.user_query_api.repositories.UserRepository;
import cqrs.event.sourcing.example.usercore.events.UserRegisteredEvent;
import cqrs.event.sourcing.example.usercore.events.UserRemovedEvent;
import cqrs.event.sourcing.example.usercore.events.UserUpdatedEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    @Autowired
    public UserEventHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserUpdatedEvent event) {
        userRepository.save(event.getUser());
    }

    @EventHandler
    @Override
    public void on(UserRemovedEvent event) {
        userRepository.deleteById(event.getId());
    }
}
