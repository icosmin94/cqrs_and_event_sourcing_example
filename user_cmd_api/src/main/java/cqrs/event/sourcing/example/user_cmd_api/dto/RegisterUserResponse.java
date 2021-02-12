package cqrs.event.sourcing.example.user_cmd_api.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class RegisterUserResponse extends BaseResponse {

    private String id;

    public RegisterUserResponse(String message) {
        super(message);
    }
}
