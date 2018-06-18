package com.nju.pet_service.dao;

import com.nju.pet_service.model.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PetDAO extends JpaRepository<PetEntity, Integer> {
    @Query("select max(entity.id) from PetEntity entity")
    Integer findMaxId();

    List<PetEntity> findPetEntitiesByCategoryId(Integer categoryId);

    List<PetEntity> findPetEntitiesByPetNameLike(String petName);

    PetEntity findPetEntityByPetName(String petName);
}
