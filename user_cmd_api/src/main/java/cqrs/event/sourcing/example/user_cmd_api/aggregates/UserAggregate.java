package cqrs.event.sourcing.example.user_cmd_api.aggregates;

import cqrs.event.sourcing.example.user_cmd_api.commands.RegisterUserCommand;
import cqrs.event.sourcing.example.user_cmd_api.commands.RemoveUserCommand;
import cqrs.event.sourcing.example.user_cmd_api.commands.UpdateUserCommand;
import cqrs.event.sourcing.example.usercore.events.UserRegisteredEvent;
import cqrs.event.sourcing.example.usercore.events.UserRemovedEvent;
import cqrs.event.sourcing.example.usercore.events.UserUpdatedEvent;
import cqrs.event.sourcing.example.usercore.models.User;
import cqrs.event.sourcing.example.usercore.security.PasswordEncoder;
import cqrs.event.sourcing.example.usercore.security.PasswordEncoderImpl;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String id;
    private User user;

    private final PasswordEncoder passwordEncoder;

    public UserAggregate() {
        this.passwordEncoder = new PasswordEncoderImpl();
    }

    @CommandHandler
    public UserAggregate(RegisterUserCommand registerUserCommand) {
        this();
        User user = registerUserCommand.getUser();
        user.setId(registerUserCommand.getId());
        String password = user.getAccount().getPassword();
        String hashedPassword = passwordEncoder.hashPassword(password);
        user.getAccount().setPassword(hashedPassword);

        UserRegisteredEvent userRegisteredEvent = UserRegisteredEvent.builder()
                .id(registerUserCommand.getId())
                .user(user)
                .build();

        AggregateLifecycle.apply(userRegisteredEvent);
    }

    @CommandHandler
    public void handle(UpdateUserCommand updateUserCommand) {
        User user = updateUserCommand.getUser();
        user.setId(updateUserCommand.getId());
        String password = user.getAccount().getPassword();
        String hashedPassword = passwordEncoder.hashPassword(password);
        user.getAccount().setPassword(hashedPassword);

        UserUpdatedEvent userUpdatedEvent = UserUpdatedEvent.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .build();

        AggregateLifecycle.apply(userUpdatedEvent);
    }

    @CommandHandler
    public void handle(RemoveUserCommand removeUserCommand) {
        UserRemovedEvent userRemovedEvent = new UserRemovedEvent();
        userRemovedEvent.setId(removeUserCommand.getId());

        AggregateLifecycle.apply(userRemovedEvent);
    }

    @EventSourcingHandler
    public void on(UserRegisteredEvent userRegisteredEvent) {
        this.id = userRegisteredEvent.getId();
        this.user = userRegisteredEvent.getUser();
    }

    @EventSourcingHandler
    public void on(UserUpdatedEvent userUpdatedEvent) {
        this.user = userUpdatedEvent.getUser();
    }

    @EventSourcingHandler
    public void on(UserRemovedEvent userRemovedEvent) {
        AggregateLifecycle.markDeleted();
    }
}
