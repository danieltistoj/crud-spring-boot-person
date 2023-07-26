package com.api.crud.services;

import com.api.crud.dto.PersonDTO;
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
    public PersonDTO findBySlug(String slug){

        List<PersonModel> people = personRepository.findBySlug(slug);
        PersonDTO personDTO =null;
        if(!people.isEmpty()){
            for (PersonModel personModel:people){
                personDTO = modelToDTO(personModel);
            }
            return personDTO;
        }
        return personDTO;
    }
    public Object savePerson(PersonModel person){
        if(findByName(person.getName()).isEmpty()){
            String slug  = Tool.nameToSlug(person.getName());
            person.setSlug(slug);
            return personRepository.save(person);
        }
       throw  new IllegalArgumentException("The person already exists");
    }
    public PersonDTO modelToDTO(PersonModel personModel){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(personModel.getName());
        personDTO.setDate(personModel.getDate());
        personDTO.setDirection(personModel.getDirection());
        personDTO.setEmail(personModel.getEmail());
        personDTO.setSlug(personModel.getSlug());
        personDTO.setPets(personModel.getPets());
        personDTO.setPhone(personModel.getPhone());

        return personDTO;
    }


}
