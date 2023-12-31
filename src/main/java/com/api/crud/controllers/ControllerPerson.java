package com.api.crud.controllers;


import com.api.crud.dto.PersonDTO;
import com.api.crud.models.PersonModel;
import com.api.crud.services.PersonService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The person does not exist");
    }


    @PostMapping
    public ResponseEntity<Object>  createPerson(@RequestBody PersonModel person){
        try{
            return ResponseEntity.ok(personService.savePerson(person));
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @PutMapping(path = "/{slug}")
    public ResponseEntity<Object> updatePerson(@RequestBody PersonDTO personDTO, @PathVariable String slug){
        if(personService.findBySlug(slug)!=null){
            try {
                return ResponseEntity.ok(personService.updatePerson(slug,personDTO));
            }catch (IllegalArgumentException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The person does not exist");

    }
    @DeleteMapping(path = "/{slug}")
    public ResponseEntity<Object> deletePerson(@PathVariable String slug){

            try {
                return ResponseEntity.ok(personService.deletePersonBySlug(slug));
            }catch (IllegalArgumentException e){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            }

    }
}
