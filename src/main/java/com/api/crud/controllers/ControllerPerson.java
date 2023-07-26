package com.api.crud.controllers;


import com.api.crud.dto.PersonDTO;
import com.api.crud.models.PersonModel;
import com.api.crud.services.PersonService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class ControllerPerson {
    @Autowired
    private PersonService personService;
    @GetMapping
    public ResponseEntity<List<PersonModel>>  everybody(){
        List<PersonModel> people = personService.allPerson();
        return ResponseEntity.ok(people);
    }

    @GetMapping( path= "/{slug}")
    public ResponseEntity<Object> findPersonBySlug(@PathVariable String slug){
         PersonDTO person = personService.findBySlug(slug);
        if(person!=null){
            return ResponseEntity.ok(person);
        }
        return ResponseEntity.ofNullable("the person does not exist");
    }


    @PostMapping
    public ResponseEntity<Object>  createPerson(@RequestBody PersonModel person){
        try{
            return ResponseEntity.ok(personService.savePerson(person));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
