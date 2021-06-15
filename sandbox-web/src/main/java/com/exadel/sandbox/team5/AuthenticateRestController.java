package com.exadel.sandbox.team5;

import com.exadel.sandbox.team5.configs.security.JwtUserDetailsService;
import com.exadel.sandbox.team5.configs.security.pojo.AuthenticationRequest;
import com.exadel.sandbox.team5.configs.security.pojo.AuthenticationResponse;
import com.exadel.sandbox.team5.configs.security.util.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthenticateRestController {

    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

    @PostMapping("/authentication")
    public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorect username or password", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
