package com.api.crud.repositories;

import com.api.crud.models.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<PersonModel,Long> {
    Optional<PersonModel> findByName(String name);
    Optional<PersonModel> findBySlug(String slug);
    Optional<PersonModel> findByEmail(String email);
}
