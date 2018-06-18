package com.nju.order_service.dao;

import com.nju.order_service.model.PetCartItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemDAO extends JpaRepository<PetCartItemEntity, Integer>{

    @Query("select max(entity.id) from PetCartItemEntity entity")
    Integer findMaxId();

    @Query("delete from PetCartItemEntity entity where entity.userId = :uid")
    void deleteCartItem(@Param("uid")Integer uid);

    List<PetCartItemEntity> findPetCartItemEntitiesByUserId(Integer userId);
}
