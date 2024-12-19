package kusu.trade.AuthService.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "AuthController", description = "контроллер для авторизации")
@RestController()
@RequestMapping("/api/web/v1/auth")
public class AuthController {

    @Operation(
            summary = "Авторизация пользователя",
            description = "Позволяет авторизировать пользователя и получить токен"
    )
    @GetMapping("/get_token")
    String getToken(){
        return "get_token";
    }

    @Operation(
            summary = "Обновление токена",
            description = "Позволяет обновить токен"
    )
    @PostMapping("/refresh_token")
    String refreshToken(){
        return "refresh_token";
    }
}
