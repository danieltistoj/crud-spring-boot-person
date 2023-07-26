package com.api.crud.repositories;

import com.api.crud.models.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPetRepository extends JpaRepository<PetModel,Long> {
    Optional<PetModel> findByName(String name);
    Optional<PetModel> findBySlug(String slug);
}
