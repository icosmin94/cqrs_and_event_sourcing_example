package cqrs.event.sourcing.example.user_cmd_api.commands;

import cqrs.event.sourcing.example.usercore.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class RegisterUserCommand {
    @TargetAggregateIdentifier
    private String id;
    private User user;
}
