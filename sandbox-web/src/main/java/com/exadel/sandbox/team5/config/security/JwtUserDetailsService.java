package com.exadel.sandbox.team5.config.security;

import com.exadel.sandbox.team5.config.security.util.JwtUser;
import com.exadel.sandbox.team5.config.security.util.JwtUserFactory;
import com.exadel.sandbox.team5.entity.Employee;
import com.exadel.sandbox.team5.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final EmployeeService employeeService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return createUserDetails(login);
    }

    public JwtUser createUserDetails(String login) {
        Employee employee = employeeService.getByLogin(login);
        return JwtUserFactory.create(employee);
    }
}
