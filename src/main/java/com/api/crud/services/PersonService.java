package com.api.crud.services;

import com.api.crud.models.PersonModel;
import com.api.crud.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    public List<PersonModel> allPerson(){
        return personRepository.findAll();
    }
    public List<PersonModel> findByName(String name){
        return personRepository.findByName(name);
    }
    public PersonModel savePerson(PersonModel person){
       return personRepository.save(person);
    }
}
