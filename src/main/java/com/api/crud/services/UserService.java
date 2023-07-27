package com.api.crud.services;

import com.api.crud.dto.UserDTO;
import com.api.crud.models.UserModel;
import com.api.crud.repositories.IUserRepository;
import com.api.crud.tools.CustomResponse;
import org.apache.catalina.User;
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

    public UserModel createUser(UserDTO userDTO){
        UserModel userModel = dtoToModel(userDTO);
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
    public UserModel dtoToModel(UserDTO userDTO){
        UserModel userModel = new UserModel();
        userModel.setUsername(userDTO.getUsername());
        userModel.setPassword(userDTO.getPassword());
        userModel.setRol(userDTO.getRol());
        userModel.setEmail(userDTO.getEmail());
        return userModel;
    }
    public CustomResponse authenticateUser(UserDTO userDTO){
        UserModel authUserModel = findByUserName(userDTO.getUsername());

        CustomResponse customResponse = new CustomResponse();
        customResponse.TimeDate();
        customResponse.setUrl("/users/login");

        if(authUserModel!=null && PasswordService.verifyPassword(userDTO.getPassword(),authUserModel.getPassword())){
            customResponse.setMessage(userDTO.getUsername());
            customResponse.setStatus(HttpStatus.OK.value());
            return customResponse;
        }
        customResponse.setMessage("The credentials are wrong");
        customResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return customResponse;
    }

}
