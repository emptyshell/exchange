package men.suruceanu.exchange.controller;

import men.suruceanu.exchange.component.JwtUtils;
import men.suruceanu.exchange.dao.Employee;
import men.suruceanu.exchange.dto.LoginRequest;
import men.suruceanu.exchange.dto.exception.RegisterEmployeeException;
import men.suruceanu.exchange.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth/employee")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public void authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        response.addHeader(HttpHeaders.AUTHORIZATION, jwt);
    }

    @PostMapping("/register")
    public void registerUser(@Valid @RequestBody Employee signUpRequest) throws RegisterEmployeeException {
        if (employeeRepository.existsByEmployeeLogin(signUpRequest.getEmployeeLogin())) {
            throw new RegisterEmployeeException("Error: Employee login is already taken!");
        }

        signUpRequest.setEmployeePassword(encoder.encode(signUpRequest.getEmployeePassword()));

        employeeRepository.save(signUpRequest);
    }
}
