package kusu.trade.AuthService.services;

import io.jsonwebtoken.Claims;
import jakarta.security.auth.message.AuthException;
import kusu.trade.AuthService.data.response.JWTResponse;
import kusu.trade.AuthService.data.request.LoginRequest;
import kusu.trade.AuthService.data.User;
import kusu.trade.AuthService.services.jwt.JWTService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class AuthService {

    private final UserService userService;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JWTService jwtService;


    public JWTResponse login(@NonNull LoginRequest authRequest) throws AuthException {
        final User user = userService.getByLogin(authRequest.getLogin());

        if (user.getPassword().equals(authRequest.getPassword())) {

            final String accessToken = jwtService.generateAccessToken(user);
            final String refreshToken = jwtService.generateRefreshToken(user);

            refreshStorage.put(user.getLogin(), refreshToken);

            return new JWTResponse(accessToken, refreshToken);
        } else {
            throw new AuthException("Неправильный пароль");
        }
    }

    public JWTResponse getAccessToken(@NonNull String refreshToken) throws AuthException {

        if (jwtService.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtService.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login);
                final String accessToken = jwtService.generateAccessToken(user);

                return new JWTResponse(accessToken, null);
            }
        }
        return new JWTResponse(null, null);
    }

    public JWTResponse refresh(@NonNull String refreshToken) throws AuthException {

        if (jwtService.validateRefreshToken(refreshToken)) {

            final Claims claims = jwtService.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);

            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {

                final User user = userService.getByLogin(login);
                final String accessToken = jwtService.generateAccessToken(user);
                final String newRefreshToken = jwtService.generateRefreshToken(user);

                refreshStorage.put(user.getLogin(), newRefreshToken);

                return new JWTResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Невалидный JWT токен");
    }

}
