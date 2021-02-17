package cqrs.event.sourcing.example.user_cmd_api.controllers;

import cqrs.event.sourcing.example.user_cmd_api.commands.RemoveUserCommand;
import cqrs.event.sourcing.example.user_cmd_api.dto.BaseResponse;
import cqrs.event.sourcing.example.user_cmd_api.dto.RegisterUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/removeUser")
public class RemoveUserController {

    private final CommandGateway commandGateway;

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<BaseResponse> removeUSer(@PathVariable(value = "id") String id) {
        try {
            commandGateway.send(new RemoveUserCommand(id));
            return new ResponseEntity<>(new BaseResponse("User successfully removed"), HttpStatus.OK);
        } catch (Exception e) {
            String safeErrorMessage = "Error while processing remove user request for id - " + id;
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(new BaseResponse(safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
