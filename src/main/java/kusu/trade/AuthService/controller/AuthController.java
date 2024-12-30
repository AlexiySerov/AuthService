package kusu.trade.AuthService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.security.auth.message.AuthException;
import kusu.trade.AuthService.data.request.RefreshTokenRequest;
import kusu.trade.AuthService.data.response.JWTResponse;
import kusu.trade.AuthService.data.request.LoginRequest;
import kusu.trade.AuthService.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AuthController", description = "контроллер для авторизации")
@RestController()
@RequestMapping("/api/web/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(
            summary = "Авторизация пользователя",
            description = "Позволяет авторизировать пользователя и получить токен"
    )
    @GetMapping("/login")
    public ResponseEntity<JWTResponse> getToken(@RequestBody LoginRequest loginRequest) throws AuthException {

        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @Operation(
            summary = "Обновление токена",
            description = "Позволяет обновить токен доступа"
    )
    @GetMapping("/new_access_token")
    public ResponseEntity<JWTResponse> newAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws AuthException {

        return ResponseEntity.ok(authService.getAccessToken(refreshTokenRequest.getRefreshToken()));
    }

    @Operation(
            summary = "Обновление токена",
            description = "Позволяет обновить токен обновления"
    )
    @GetMapping("/new_refresh_token")
    public ResponseEntity<JWTResponse> newRefreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) throws AuthException {

        return ResponseEntity.ok(authService.refresh(refreshTokenRequest.getRefreshToken()));
    }

}
