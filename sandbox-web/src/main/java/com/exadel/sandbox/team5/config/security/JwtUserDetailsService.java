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

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final EmployeeService employeeService;

//    UserDetailsService описывается как основной интерфейс, который загружает пользовательские данные
//    в документации Spring.


//    loadUserByUsername принимает имя пользователя в качестве параметра и возвращает объект
//    идентификации пользователя.

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {
            return createUserDetails(login);
        } catch (NoSuchElementException e) {
            throw new UsernameNotFoundException("Login invalid");
        }
    }

    public JwtUser createUserDetails(String login) {
        Employee employee = employeeService.getByLogin(login);
        return JwtUserFactory.create(employee);
    }
}
