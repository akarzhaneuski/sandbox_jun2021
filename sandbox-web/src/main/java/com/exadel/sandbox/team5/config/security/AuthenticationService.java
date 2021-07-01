package com.exadel.sandbox.team5.config.security;

import com.exadel.sandbox.team5.config.security.pojo.Token;

public interface AuthenticationService {

    void authenticate(String login, String password);

    Token createToken(String username);
}
