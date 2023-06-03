package com.finalexample.demo.controller;


import com.finalexample.demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Objects;

@Controller
public class BaseController {

    @Autowired
    private MenuService menuService;

    public String ok(String viewName, Model model) {
        String username = extractUsername();
        String authority = extractAuthority();

        model.addAttribute("username", username);
        model.addAttribute("menu_list", menuService.retrieveMenuByAuthority(authority));

        return viewName;
    }

    protected String extractAuthority() {
        if (Objects.isNull(getPrincipal())) {
            return "ANONYMOUS";
        }

        // Cast authority to GrantedAuthority
        GrantedAuthority grantedAuthority = (GrantedAuthority)
                getPrincipal().getAuthorities().toArray()[0];

        // Return role
        return grantedAuthority.getAuthority();
    }

    private UsernamePasswordAuthenticationToken getPrincipal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        }

        return (UsernamePasswordAuthenticationToken) authentication;
    }

    protected String extractUsername() {
        if (Objects.isNull(getPrincipal())) {
            return "";
        }

        return getPrincipal().getName();
    }

    public String success(String successMessage, Model model) {
        String username = extractUsername();
        String authority = extractAuthority();

        model.addAttribute("successMessage", successMessage);
        model.addAttribute("username", username);
        model.addAttribute("menu_list", menuService.retrieveMenuByAuthority(authority));

        return "success";
    }

    public String error(String errorMessage, Model model) {
        String username = extractUsername();
        String authority = extractAuthority();

        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("username", username);
        model.addAttribute("menu_list", menuService.retrieveMenuByAuthority(authority));

        return "error";
    }
}
