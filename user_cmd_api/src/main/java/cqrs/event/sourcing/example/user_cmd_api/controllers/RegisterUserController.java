package cqrs.event.sourcing.example.user_cmd_api.controllers;

import cqrs.event.sourcing.example.user_cmd_api.commands.RegisterUserCommand;
import cqrs.event.sourcing.example.user_cmd_api.dto.RegisterUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<RegisterUserResponse> registerUser(RegisterUserCommand command) {
        command.setId(UUID.randomUUID().toString());
        try {
            commandGateway.sendAndWait(command);
            return new ResponseEntity<>(new RegisterUserResponse("User successfully registered"), HttpStatus.CREATED);
        } catch (Exception e) {
            String safeErrorMessage = "Error while processing register user request for id - " + command.getId();
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(new RegisterUserResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
