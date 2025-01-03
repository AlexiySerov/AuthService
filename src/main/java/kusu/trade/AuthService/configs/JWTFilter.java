package kusu.trade.AuthService.configs;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import kusu.trade.AuthService.services.jwt.JWTAuthentication;
import kusu.trade.AuthService.services.jwt.JWTService;
import kusu.trade.AuthService.services.jwt.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JWTFilter extends GenericFilterBean {

    private static final String AUTHORIZATION = "Authorization";

    private final JWTService jwtService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain fc)
            throws IOException, ServletException {

        final String token = getTokenFromRequest((HttpServletRequest) request);

        if (token != null && jwtService.validateAccessToken(token)) {
            final Claims claims = jwtService.getAccessClaims(token);
            final JWTAuthentication jwtInfoToken = JWTUtil.generate(claims);

            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        }

        fc.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);

        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }

        return null;
    }

}
