package cqrs.event.sourcing.example.user_query_api.handlers;

import cqrs.event.sourcing.example.user_query_api.dto.UserLookUpResponse;
import cqrs.event.sourcing.example.user_query_api.queries.FindAllUsersQuery;
import cqrs.event.sourcing.example.user_query_api.queries.FindUserByIdQuery;
import cqrs.event.sourcing.example.user_query_api.queries.SearchUsersQuery;

public interface UserQueryHandler {
    UserLookUpResponse getUserById(FindUserByIdQuery query);
    UserLookUpResponse searchUsers(SearchUsersQuery query);
    UserLookUpResponse findAllUsers(FindAllUsersQuery query);
}
