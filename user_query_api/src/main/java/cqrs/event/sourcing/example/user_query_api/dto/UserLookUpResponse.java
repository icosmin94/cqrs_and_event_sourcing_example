package cqrs.event.sourcing.example.user_query_api.dto;

import cqrs.event.sourcing.example.usercore.models.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserLookUpResponse extends BaseResponse   {
    private List<User> users;

    public UserLookUpResponse(String message) {
        super(message);
        users = new ArrayList<>();
    }

    public UserLookUpResponse() {
        super(null);
        users = new ArrayList<>();
    }

    public UserLookUpResponse(List<User> users) {
        this(null, users);
    }

    public UserLookUpResponse(String message, List<User> users) {
        super(message);
        this.users = users;
    }

    public UserLookUpResponse(String message, User user) {
        super(message);
        this.users = new ArrayList<>();
        this.users.add(user);
    }

    public UserLookUpResponse(User user) {
        super(null);
        this.users = Collections.singletonList(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
