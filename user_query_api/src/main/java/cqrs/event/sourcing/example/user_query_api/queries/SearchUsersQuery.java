package cqrs.event.sourcing.example.user_query_api.queries;

import lombok.Data;

@Data
public class SearchUsersQuery {
    private String filter;
}
