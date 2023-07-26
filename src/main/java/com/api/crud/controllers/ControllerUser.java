package com.api.crud.controllers;

import com.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ControllerUser {
    @Autowired
    private UserService userService;
}
