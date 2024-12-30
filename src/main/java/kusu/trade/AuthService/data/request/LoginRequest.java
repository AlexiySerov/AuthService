package kusu.trade.AuthService.data.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;


@RequiredArgsConstructor
@Setter
@Getter
public class LoginRequest {

    private String login;
    private String password;
}
