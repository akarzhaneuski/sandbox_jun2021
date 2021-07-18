package com.exadel.sandbox.team5.config.security;

import com.exadel.sandbox.team5.dto.AuthorizationUserDto;

public interface AuthenticationService {

    void authenticate(String login, String password);

    AuthorizationUserDto createToken(String username);
}
