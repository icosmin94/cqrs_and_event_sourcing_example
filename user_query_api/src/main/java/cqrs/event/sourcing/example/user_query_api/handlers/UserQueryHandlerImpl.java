package cqrs.event.sourcing.example.user_query_api.handlers;

import cqrs.event.sourcing.example.user_query_api.dto.UserLookUpResponse;
import cqrs.event.sourcing.example.user_query_api.queries.FindAllUsersQuery;
import cqrs.event.sourcing.example.user_query_api.queries.FindUserByIdQuery;
import cqrs.event.sourcing.example.user_query_api.queries.SearchUsersQuery;
import cqrs.event.sourcing.example.user_query_api.repositories.UserRepository;
import cqrs.event.sourcing.example.usercore.models.User;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    @Override
    @QueryHandler
    public UserLookUpResponse getUserById(FindUserByIdQuery query) {
        Optional<User> optionalUser = userRepository.findById(query.getId());
        return optionalUser.map(user -> new UserLookUpResponse(Collections.singletonList(user))).
                orElseGet(UserLookUpResponse::new);
    }

    @Override
    @QueryHandler
    public UserLookUpResponse searchUsers(SearchUsersQuery query) {
        List<User> users = new ArrayList<>(userRepository.findByFilterRegex(query.getFilter()));
        return new UserLookUpResponse(users);
    }

    @Override
    @QueryHandler
    public UserLookUpResponse findAllUsers(FindAllUsersQuery query) {
        List<User> users = new ArrayList<>(userRepository.findAll());
        return new UserLookUpResponse(users);
    }
}
