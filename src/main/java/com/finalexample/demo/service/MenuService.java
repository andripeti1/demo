package com.finalexample.demo.service;


import com.finalexample.demo.model.MyMenu;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class MenuService {

    private static final String ANONYMOUS = "ANONYMOUS";

    public static final String USER = "USER";

    public static final String ADMIN = "ADMIN";

    private static final List<MyMenu> ANONYMOUS_MENU = Arrays.asList(
            new MyMenu("Homepage", "/"),
            new MyMenu("Cars", "/cars"),
            new MyMenu("Contact Us", "/contact"),
            new MyMenu("Login", "/login"),
            new MyMenu("Register", "/register")
    );

    private static final List<MyMenu> USER_MENU = Arrays.asList(
            new MyMenu("Homepage", "/"),
            new MyMenu("Cars", "/cars"),
            new MyMenu("My Reservations","/user/reserve"),
            new MyMenu("Contact Us", "/contact"),
            new MyMenu("Logout", "/logout")
    );

    private static final List<MyMenu> ADMIN_MENU = Arrays.asList(
            new MyMenu("Admin Panel", "/admin"),
            new MyMenu("Logout", "/logout")
    );



    public List<MyMenu> retrieveMenuByAuthority(String authority){

        switch (authority){
            case ANONYMOUS:
                return ANONYMOUS_MENU;

            case USER:
               return USER_MENU;

            case ADMIN:
                return ADMIN_MENU;
        }

        return ANONYMOUS_MENU;

    }

}
