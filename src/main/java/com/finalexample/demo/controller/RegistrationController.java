package com.finalexample.demo.controller;

import com.finalexample.demo.model.entity.MyUser;
import com.finalexample.demo.model.request.RegisterUserRequest;
import com.finalexample.demo.model.response.MyReservationsResponse;
import com.finalexample.demo.repository.UserRepository;
import com.finalexample.demo.service.MenuService;
import com.finalexample.demo.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@Controller
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController extends BaseController {

    private final UserRepository userRepository;
    private final RegistrationService registrationService;

        @GetMapping("")
        public String showRegistrationForm(Model model) {
        model.addAttribute("registrationForm", new RegisterUserRequest());
        return ok("registration-form",model);
    }

        @PostMapping("")
        public String processRegistrationForm(@ModelAttribute("registrationForm") @Valid RegisterUserRequest registrationForm,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration-form";
        }

        if (!registrationForm.getPassword().equals(registrationForm.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.registrationForm", "Passwords do not match");
            return "registration-form";
        }

        if (userRepository.existsByUsername(registrationForm.getUsername())) {
            bindingResult.rejectValue("username", "error.registrationForm", "Username already exists");
            return "registration-form";
        }

        if (userRepository.existsByEmail(registrationForm.getEmail())) {
            bindingResult.rejectValue("email", "error.registrationForm", "Email already exists");
            return "registration-form";
        }

        registrationService.registerUser(registrationForm);

        return "success";


}

}