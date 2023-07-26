package com.api.crud.services;


import com.api.crud.models.PersonModel;
import com.api.crud.models.PetModel;
import com.api.crud.repositories.IPersonRepository;
import com.api.crud.repositories.IPetRepository;
import com.api.crud.tools.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
@Service
public class  PetService  {

    @Autowired
    private IPetRepository petRepository;
    @Autowired
    private IPersonRepository personRepository;
    public List<PetModel> allPerson(){
        return petRepository.findAll();
    }

    public PetModel findByName(String name){
        PetModel petModel = checkModel(petRepository.findByName(name));
        return petModel;
    }
    public PetModel findBySlug(String slug){
        PetModel petModel = checkModel(petRepository.findBySlug(slug));
        return petModel;
    }

    private PetModel checkModel(Optional<PetModel> optionalPetModel){
        PetModel petModel = null;
        if(optionalPetModel.isPresent()){
            petModel = optionalPetModel.get();
            return petModel;
        }
        return petModel;
    }

    public PetModel savePet(PetModel petModel){
        if(findByName(petModel.getName())==null){
            String slug  = Tool.nameToSlug(petModel.getName());
            petModel.setSlug(slug);
            if(petModel.getPerson()!=null){
                Optional<PersonModel> personModelOptional = personRepository.findBySlug(petModel.getPerson().getSlug());
                if(personModelOptional.isPresent()){
                    PersonModel personModel = personModelOptional.get();
                    petModel.setPerson(personModel);
                }else{
                    throw new IllegalArgumentException("The person does not exist");
                }
            }
            return petRepository.save(petModel);
        }
        throw  new IllegalArgumentException("The pet already exists");
    }
    public PetModel updatePet(PetModel updatePet,PetModel oldPet){
        if(!oldPet.getName().equals(updatePet.getName())&&updatePet.getName()!=null){
            if(findByName(updatePet.getName())==null){
                oldPet.setName(updatePet.getName());
                oldPet.setSlug(Tool.nameToSlug(updatePet.getName()));
            }else {
                throw new IllegalArgumentException("Pet name already exists");
            }
        }
        if(updatePet.getAge()!=0){
            oldPet.setAge(updatePet.getAge());
        }
        if (updatePet.getRace()!=null){
            oldPet.setRace(updatePet.getRace());
        }
        if(updatePet.getSpecies()!=null){
            oldPet.setSpecies(updatePet.getSpecies());
        }
        if(updatePet.getPerson()!=null){
            Optional<PersonModel> personModelOptional = personRepository.findBySlug(updatePet.getPerson().getSlug());
            if(personModelOptional.isPresent()){
               oldPet.setPerson(personModelOptional.get());
            }else{
                throw new IllegalArgumentException("The person does not exist");
            }
        }

        return petRepository.save(oldPet);

    }
    public Object deletePet(PetModel petModel){
        petRepository.delete(petModel);
        return "Pet successfully deleted";
    }

}
