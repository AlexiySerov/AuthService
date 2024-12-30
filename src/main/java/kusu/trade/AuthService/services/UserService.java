package kusu.trade.AuthService.services;

import jakarta.security.auth.message.AuthException;
import kusu.trade.AuthService.data.Role;
import kusu.trade.AuthService.data.User;
import kusu.trade.AuthService.repository.UserRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;



    public User getByLogin(@NonNull String login) throws AuthException {
        return userRepository.getUserByLogin(login);
    }
}
