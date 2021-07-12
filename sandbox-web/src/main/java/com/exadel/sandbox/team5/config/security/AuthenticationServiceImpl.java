package com.exadel.sandbox.team5.config.security;

import com.exadel.sandbox.team5.config.security.pojo.Token;
import com.exadel.sandbox.team5.config.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @Override
    public void authenticate(String login, String password) {
        if (!authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)).isAuthenticated()) {
            throw new BadCredentialsException("Incorrect username or password");
        }
    }

    @Override
    public Token createToken(String username) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = "Bearer " + jwtUtil.generateToken(userDetails);
        return new Token(jwt);
    }
}
