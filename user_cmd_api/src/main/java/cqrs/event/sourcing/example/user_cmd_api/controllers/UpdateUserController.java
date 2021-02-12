package cqrs.event.sourcing.example.user_cmd_api.controllers;

import cqrs.event.sourcing.example.user_cmd_api.commands.UpdateUserCommand;
import cqrs.event.sourcing.example.user_cmd_api.dto.BaseResponse;
import cqrs.event.sourcing.example.user_cmd_api.dto.RegisterUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/updateUser")
public class UpdateUserController {
    private final CommandGateway commandGateway;

    public UpdateUserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable(value = "id") String id,
                                                   @Valid @RequestBody UpdateUserCommand command) {
        command.setId(id);
        try {
            commandGateway.send(command);
            return new ResponseEntity<>(new RegisterUserResponse("User successfully updated", id), HttpStatus.CREATED);
        } catch (Exception e) {
            String safeErrorMessage = "Error while processing update user request for id - " + command.getId();
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(new RegisterUserResponse(safeErrorMessage, id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
