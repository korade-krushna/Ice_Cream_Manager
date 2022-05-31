package com.icecream.config;

import com.icecream.dao.EmployeeRepository;
import com.icecream.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmployeeDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override // method for find username by username
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = this.employeeRepository.getEmployeebyUsername(username);
        if (employee == null) {
            throw new UsernameNotFoundException("employee dose not exist");
        }
        return new CustomEmployeeDetails(employee);
    }
}
