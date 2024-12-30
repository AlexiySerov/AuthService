package kusu.trade.AuthService.services.jwt;

import io.jsonwebtoken.Claims;
import kusu.trade.AuthService.data.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JWTUtil {

    public static JWTAuthentication generate(Claims claims) {
        final JWTAuthentication jwtInfoToken = new JWTAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFirstName(claims.get("firstName", String.class));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static Set<Role> getRoles(Claims claims) {
        final List<String> roles = claims.get("role", List.class);
        return roles.stream()
                .map(Role::valueOf)
                .collect(Collectors.toSet());
    }

}
