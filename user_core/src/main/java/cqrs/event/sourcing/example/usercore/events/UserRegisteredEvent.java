package cqrs.event.sourcing.example.usercore.events;

import cqrs.event.sourcing.example.usercore.models.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisteredEvent {
    private String id;
    private User user;
}
