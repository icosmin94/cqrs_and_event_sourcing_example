package cqrs.event.sourcing.example.user_query_api.handlers;

import cqrs.event.sourcing.example.usercore.events.UserRegisteredEvent;
import cqrs.event.sourcing.example.usercore.events.UserRemovedEvent;
import cqrs.event.sourcing.example.usercore.events.UserUpdatedEvent;

public interface UserEventHandler  {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
