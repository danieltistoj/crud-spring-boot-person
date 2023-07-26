package com.api.crud.repositories;

import com.api.crud.models.PetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IPetRepository extends JpaRepository<PetModel,Long> {
    Optional<PetModel> findByName(String name);
    Optional<PetModel> findBySlug(String slug);
}
