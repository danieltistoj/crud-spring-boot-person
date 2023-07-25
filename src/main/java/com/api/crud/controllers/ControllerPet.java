package com.api.crud.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pets")
public class ControllerPet {
    @GetMapping
    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }

}
