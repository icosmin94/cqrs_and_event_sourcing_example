package cqrs.event.sourcing.example.user_cmd_api.commands;

import cqrs.event.sourcing.example.usercore.models.User;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String id;
    @NotNull
    @Valid
    private User user;
}
