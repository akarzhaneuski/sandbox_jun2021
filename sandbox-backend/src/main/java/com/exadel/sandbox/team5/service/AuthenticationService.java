package com.exadel.sandbox.team5.service;

import com.exadel.sandbox.team5.configs.security.pojo.Token;

public interface AuthenticationService {

    void authenticate(String login, String password);

    Token createToken(String username);
}
