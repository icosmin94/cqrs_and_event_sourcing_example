package cqrs.event.sourcing.example.user_cmd_api.controllers;

import cqrs.event.sourcing.example.user_cmd_api.commands.RegisterUserCommand;
import cqrs.event.sourcing.example.user_cmd_api.dto.RegisterUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/registerUser")
public class RegisterUserController {

    private final CommandGateway commandGateway;

    public RegisterUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody @Valid RegisterUserCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new RegisterUserResponse("User successfully registered", id), HttpStatus.CREATED);
        } catch (Exception e) {
            String safeErrorMessage = "Error while processing register user request for id - " + command.getId();
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(new RegisterUserResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
