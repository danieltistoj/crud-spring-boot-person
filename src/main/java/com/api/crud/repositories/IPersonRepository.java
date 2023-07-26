package com.api.crud.repositories;

import com.api.crud.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPersonRepository extends JpaRepository<PersonModel,Long> {
    List<PersonModel> findByName(String name);
    List<PersonModel> findBySlug(String slug);
}
