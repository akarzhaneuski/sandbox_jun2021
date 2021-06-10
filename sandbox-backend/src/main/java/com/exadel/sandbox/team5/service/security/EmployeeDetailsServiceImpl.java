package com.exadel.sandbox.team5.service.security;

import com.exadel.sandbox.team5.dao.EmployeeDAO;
import com.exadel.sandbox.team5.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDetailsServiceImpl implements UserDetailsService {
    @Autowired
    EmployeeDAO employeeDAO;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Employee employee = employeeDAO.findByLogin(login).
                orElseThrow(() -> new UsernameNotFoundException("Employee not found with login : " + login));
        return EmployeeDetailsImpl.build(employee);
    }
}
