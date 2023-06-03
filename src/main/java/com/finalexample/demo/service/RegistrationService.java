package com.finalexample.demo.service;

import com.finalexample.demo.model.entity.MyUser;
import com.finalexample.demo.model.exception.UserRegistrationException;
import com.finalexample.demo.model.request.RegisterUserRequest;
import com.finalexample.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterUserRequest registrationForm) throws UserRegistrationException {
        validateRegistrationForm(registrationForm);

        String username = registrationForm.getUsername();
        if (userRepository.existsByUsername(username)) {
            throw new UserRegistrationException("Username already exists");
        }

        String email = registrationForm.getEmail();
        if (userRepository.existsByEmail(email)) {
            throw new UserRegistrationException("Email already exists");
        }

        MyUser user = new MyUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        user.setEmail(email);
        user.setAuthority("USER");

        userRepository.save(user);
    }

    private void validateRegistrationForm(RegisterUserRequest registrationForm) throws UserRegistrationException {
        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            throw new UserRegistrationException("Passwords do not match");
        }
    }
}
