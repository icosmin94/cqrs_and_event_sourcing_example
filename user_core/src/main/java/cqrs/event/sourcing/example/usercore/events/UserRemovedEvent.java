package cqrs.event.sourcing.example.usercore.events;

import lombok.Data;

@Data
public class UserRemovedEvent {
    public String id;
}
