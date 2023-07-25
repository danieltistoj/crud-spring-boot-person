package com.api.crud.controllers;


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
    @PostMapping
    public ResponseEntity<PersonModel>  createPerson(@RequestBody PersonModel person){
        return ResponseEntity.ok(personService.savePerson(person));
    }
}
