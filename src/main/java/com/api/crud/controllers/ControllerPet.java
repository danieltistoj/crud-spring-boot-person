package com.api.crud.controllers;

import com.api.crud.models.PersonModel;
import com.api.crud.models.PetModel;
import com.api.crud.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class ControllerPet {
    @Autowired
    private PetService petService;
    @GetMapping
   public ResponseEntity<List<PetModel>> allPet(){
        return ResponseEntity.ok(petService.allPerson());
    }
    @GetMapping(path = "/{slug}")
    public ResponseEntity<Object> findBySlug(@PathVariable String slug){
        PetModel petModel = petService.findBySlug(slug);
        if(petModel!=null){
            return ResponseEntity.ok(petModel);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The pet does not exist");
    }
    @PostMapping
    public ResponseEntity<Object>  createPet(@RequestBody PetModel pet){
        try{
            return ResponseEntity.ok(petService.savePet(pet));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping(path = "/{slug}")
    public ResponseEntity<Object> updadtePet(@RequestBody PetModel pet, @PathVariable String slug){
        PetModel personModel = petService.findBySlug(slug);
        if(personModel!=null){
            try {
             return ResponseEntity.ok(petService.updatePet(pet,personModel));
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The pet does not exist");
    }

}
