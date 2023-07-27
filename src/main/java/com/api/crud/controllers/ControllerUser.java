package com.api.crud.controllers;

import com.api.crud.models.UserModel;
import com.api.crud.services.UserService;
import com.api.crud.tools.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class ControllerUser {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Object> SignIn(@RequestBody UserModel userModel){
        if(userService.findByUserName(userModel.getUsername())==null){
            if(userService.findByEmail(userModel.getEmail())==null){
                return ResponseEntity.ok(userService.createUser(userModel));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("the email already exists");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username already exist");
    }
    @PostMapping(path = "/login")
    public ResponseEntity<CustomResponse> LogIn(@RequestBody UserModel userModel){
        CustomResponse customResponse = userService.authenticateUser(userModel);
        if(customResponse.getStatus()==200){
            return ResponseEntity.ok(customResponse);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customResponse);
    }
    @GetMapping(path = "/{username}")
    public ResponseEntity<Object> findByUserName(@PathVariable String username){
        UserModel userName = userService.findByUserName(username);
        UserModel userEmail = userService.findByEmail(username);

        if(userName!=null){
            return ResponseEntity.ok(userName);
        }
        if(userEmail!=null){
            return ResponseEntity.ok(userEmail);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Username does not exist");
    }
    @GetMapping
    public ResponseEntity<List<UserModel>> allUser(){
        return ResponseEntity.ok(userService.allUser());
    }

}
