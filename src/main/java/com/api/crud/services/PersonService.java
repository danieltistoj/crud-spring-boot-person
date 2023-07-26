package com.api.crud.services;

import com.api.crud.dto.PersonDTO;
import com.api.crud.models.PersonModel;
import com.api.crud.repositories.IPersonRepository;
import com.api.crud.tools.Tool;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private IPersonRepository personRepository;
    public List<PersonModel> allPerson(){
        return personRepository.findAll();
    }

    public PersonDTO findByName(String name){

        return extractPerson(personRepository.findByName(name));
    }
    public PersonDTO findBySlug(String slug){

        return extractPerson(personRepository.findBySlug(slug));
    }
    public  PersonDTO findByEmail(String email){
        return extractPerson(personRepository.findByEmail(email));
    }

    public Object savePerson(PersonModel person){
        if(findByName(person.getName())==null){
            String slug  = Tool.nameToSlug(person.getName());
            person.setSlug(slug);
            return personRepository.save(person);
        }
       throw  new IllegalArgumentException("The person already exists");
    }

    public Object updatePerson(String slug,PersonDTO personDTO){
        PersonModel personModel = personRepository.findBySlug(slug).get();
        if(!personModel.getName().equals(personDTO.getName()) && personDTO.getName()!=null){
            if(findByName(personDTO.getName())==null){
                personModel.setName(personDTO.getName());
                personModel.setSlug(Tool.nameToSlug(personDTO.getName()));
            }else{
                throw new IllegalArgumentException("there is already a person with this name");
            }
        }
        if(!personModel.getEmail().equals(personDTO.getEmail()) && personDTO.getEmail()!=null){
            if(findByEmail(personDTO.getEmail())==null){
                personModel.setName(personDTO.getName());
            }else{
                throw new IllegalArgumentException("there is already a person with this email");
            }
        }
        if(personDTO.getDirection()!=null){
            personModel.setDirection(personDTO.getDirection());
        }
        if (personDTO.getDate()!=null){
            personModel.setDate(personDTO.getDate());
        }
        if(personDTO.getPhone()!=null){
            personModel.setPhone(personDTO.getPhone());
        }
        personRepository.save(personModel);
        return personModel;
    }


    public PersonDTO extractPerson(Optional<PersonModel> personModel){
        PersonDTO personDTO =null;
        if(personModel.isPresent()){
            personDTO = modelToDTO(personModel.get());
            return personDTO;
        }
        return personDTO;
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
