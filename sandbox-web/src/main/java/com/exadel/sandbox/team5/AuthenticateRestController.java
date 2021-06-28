package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.configs.security.pojo.AuthenticationRequest;
import com.exadel.sandbox.team5.configs.security.pojo.Token;
import com.exadel.sandbox.team5.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticateRestController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authentication")
    public ResponseEntity<Token> createToken(@RequestBody AuthenticationRequest request) {
        authenticationService.authenticate(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(authenticationService.createToken(request.getUsername()));
    }
}
