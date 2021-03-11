package men.suruceanu.exchange.service;

import men.suruceanu.exchange.dao.Employee;
import men.suruceanu.exchange.dto.UserDetailsImpl;
import men.suruceanu.exchange.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String employeeLogin) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmployeeLogin(employeeLogin).orElseThrow(
                () -> new UsernameNotFoundException("Employee Not Found with login: " + employeeLogin)
        );

        return UserDetailsImpl.build(employee);
    }
}
