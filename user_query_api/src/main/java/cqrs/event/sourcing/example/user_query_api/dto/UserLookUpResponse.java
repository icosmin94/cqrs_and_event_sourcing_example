package cqrs.event.sourcing.example.user_query_api.dto;

import cqrs.event.sourcing.example.usercore.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class UserLookUpResponse {
    private List<User> users;

    public UserLookUpResponse() {
        users = new ArrayList<>();
    }
}
