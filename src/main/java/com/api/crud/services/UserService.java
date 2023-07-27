package com.api.crud.services;

import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

}
