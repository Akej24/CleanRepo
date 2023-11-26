package com.codepred.cleanrepo.auth;

import com.codepred.cleanrepo.auth.exception.InvalidJwtException;
import com.codepred.cleanrepo.auth.exception.UserEmailNotFoundException;
import com.codepred.cleanrepo.auth.value_object.Jwt;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
class LoginFilterConfig extends OncePerRequestFilter {

    private final AccountCredentialsRepository accountCredentialsRepository;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if (!validateAuthHeader(request, response, filterChain, authHeader)) return;

        Jwt jwtFromHeader = Jwt.from(authHeader.substring(7));
        String jwtEmail = jwtService.extractUserEmailFromJwt(jwtFromHeader);

        var userDetails = this.userDetailsService.loadUserByUsername(jwtEmail);
        var accountCredentials = getUserCredentials(jwtEmail);

        if (!jwtService.validateJwt(accountCredentials, jwtFromHeader)) throw new InvalidJwtException();
        setAuthTokenToSecurityContext(request, userDetails, accountCredentials);
        filterChain.doFilter(request, response);
    }

    private boolean validateAuthHeader(HttpServletRequest request,
                                       HttpServletResponse response,
                                       FilterChain filterChain,
                                       String authHeader
    ) throws ServletException, IOException {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return false;
        }
        return true;
    }

    private static void setAuthTokenToSecurityContext(HttpServletRequest request,
                                                      UserDetails userDetails,
                                                      AccountCredentials accountCredentials
    ) {
        var authToken = new UsernamePasswordAuthenticationToken(accountCredentials, null, userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }

    private AccountCredentials getUserCredentials(String email) {
        return accountCredentialsRepository
                .findByEmail_Email(email)
                .orElseThrow(UserEmailNotFoundException::new);
    }
}
