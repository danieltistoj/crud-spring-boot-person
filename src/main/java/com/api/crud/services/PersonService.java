package com.api.crud.services;

import com.api.crud.models.PersonModel;
import com.api.crud.repositories.IPersonRepository;
import com.api.crud.tools.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    @Autowired
    private IPersonRepository personRepository;
    public List<PersonModel> allPerson(){
        return personRepository.findAll();
    }
    public List<PersonModel> findByName(String name){
        return personRepository.findByName(name);
    }
    public List<PersonModel> findBySlug(String slug){
        return personRepository.findBySlug(slug);
    }
    public Object savePerson(PersonModel person){
        if(findByName(person.getName()).isEmpty()){
            String slug  = Tool.nameToSlug(person.getName());

            person.setSlug(slug);
            return personRepository.save(person);
        }
       throw  new IllegalArgumentException("The person already exists");
    }


}
