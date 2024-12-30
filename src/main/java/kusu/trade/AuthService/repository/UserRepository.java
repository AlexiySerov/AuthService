package kusu.trade.AuthService.repository;

import jakarta.security.auth.message.AuthException;
import jakarta.validation.constraints.NotNull;
import kusu.trade.AuthService.data.Role;
import kusu.trade.AuthService.data.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class UserRepository {
    private final List<User> users = List.of(
            new User("anton", "1234",Role.USER.getAuthority()),
            new User("ivan", "12345",  Role.ADMIN.getAuthority())
            );

    /**
     * find user by login
     *
     * @param login
     * @return User
     * @throws NoSuchElementException have no user with this login
     */
    public User getUserByLogin(@NotNull String login) throws AuthException {
        return users.stream()
                .filter(user -> login.equals(user.getLogin()))
                .findFirst().orElseThrow(() -> new AuthException("Пользователь не найден"));
    }
}
