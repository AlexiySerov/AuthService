package kusu.trade.AuthService.data;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    private String login;
    private String password;
    private String role;

}
