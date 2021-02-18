package cqrs.event.sourcing.example.user_query_api.controllers;

import cqrs.event.sourcing.example.user_query_api.dto.UserLookUpResponse;
import cqrs.event.sourcing.example.user_query_api.queries.FindAllUsersQuery;
import cqrs.event.sourcing.example.user_query_api.queries.FindUserByIdQuery;
import cqrs.event.sourcing.example.user_query_api.queries.SearchUsersQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/userLookup")
public class UserLookupController {

    private final QueryGateway queryGateway;

    @GetMapping(path = "/")
    public ResponseEntity<UserLookUpResponse> getAllUsers() {
        try {
            FindAllUsersQuery allUsersQuery = new FindAllUsersQuery();
            UserLookUpResponse userLookUpResponse = queryGateway
                    .query(allUsersQuery, ResponseTypes.instanceOf(UserLookUpResponse.class)).join();
            return new ResponseEntity<>(userLookUpResponse, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Failed to complete all users request";
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(new UserLookUpResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "byId/{id}")
    public ResponseEntity<UserLookUpResponse> findById(@PathVariable(value = "id") String id) {
        try {
            FindUserByIdQuery findUserByIdQuery = new FindUserByIdQuery(id);
            UserLookUpResponse userLookUpResponse = queryGateway
                    .query(findUserByIdQuery, ResponseTypes.instanceOf(UserLookUpResponse.class)).join();
            return new ResponseEntity<>(userLookUpResponse, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Failed to complete all users request";
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(new UserLookUpResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "byFilter/{filter}")
    public ResponseEntity<UserLookUpResponse> findByFilter(@PathVariable(value = "filter") String filter) {
        try {
            SearchUsersQuery searchUsersQuery = new SearchUsersQuery(filter);
            UserLookUpResponse userLookUpResponse = queryGateway
                    .query(searchUsersQuery, ResponseTypes.instanceOf(UserLookUpResponse.class)).join();
            return new ResponseEntity<>(userLookUpResponse, HttpStatus.OK);
        } catch (Exception e) {
            String errorMessage = "Failed to complete all users request";
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(new UserLookUpResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
