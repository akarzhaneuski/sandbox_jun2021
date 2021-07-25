package com.exadel.sandbox.team5.config.security;

import com.exadel.sandbox.team5.config.security.util.JwtUser;
import com.exadel.sandbox.team5.config.security.util.JwtUtil;
import com.exadel.sandbox.team5.dto.AuthorizationUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
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
        try {
            if (!authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(login, password)).isAuthenticated()) {
                throw new BadCredentialsException("Incorrect username or password");
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("User not found");
        }
    }

    @Override
    public AuthorizationUserDto createToken(String username) {
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final JwtUser user = userDetailsService.createUserDetails(username);
        final String jwt = "Bearer " + jwtUtil.generateToken(userDetails);
        return new AuthorizationUserDto(jwt, user.getLocation(), user.getAuthorities());
    }
}
