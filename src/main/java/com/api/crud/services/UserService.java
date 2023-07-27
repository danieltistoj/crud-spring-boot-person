package com.api.crud.services;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import com.api.crud.tools.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    private PasswordService passwordService;

    public UserModel createUser(UserModel userModel){
        userModel.setPassword(PasswordService.encryptPassword(userModel.getPassword()));
        return userRepository.save(userModel);
    }
    public UserModel findByUserName(String username){
        return checkModel(userRepository.findByUsername(username));
    }
    public UserModel findByEmail(String email){
        return checkModel(userRepository.findByEmail(email));
    }
    public List<UserModel> allUser(){
        return userRepository.findAll();
    }
    public UserModel checkModel(Optional<UserModel> userModelOptional){
        UserModel userModel=null;
        if(userModelOptional.isPresent()){
            userModel = userModelOptional.get();
        }
        return userModel;
    }
    public CustomResponse authenticateUser(UserModel userModel){
        UserModel authUserModel = findByUserName(userModel.getUsername());

        CustomResponse customResponse = new CustomResponse();
        customResponse.TimeDate();
        customResponse.setUrl("/users/login");

        if(authUserModel!=null && PasswordService.verifyPassword(userModel.getPassword(),authUserModel.getPassword())){
            customResponse.setMessage(userModel.getUsername());
            customResponse.setStatus(HttpStatus.OK.value());
            return customResponse;
        }
        customResponse.setMessage("The credentials are wrong");
        customResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return customResponse;
    }

}
