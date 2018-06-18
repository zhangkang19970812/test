package com.nju.pet_service.service;

import com.nju.pet_service.dao.PetDAO;
import com.nju.pet_service.restController.info.PetInfo;
import com.nju.pet_service.restController.info.PetListInfo;
import com.nju.pet_service.model.PetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    PetDAO petDAO;

    @Override
    public int addPet(PetInfo petInfo) {
        Integer id = petInfo.getId();
        PetEntity petEntity = null;
        if (id == -1) {
            id = petDAO.findMaxId();
            if (id == null) id = 0;
            id++;
            petEntity = new PetEntity();
            petEntity.setId(id);
        } else {
            petEntity = petDAO.getOne(id);
        }
        petEntity.setCategoryId(petInfo.getCategory_id());
        petEntity.setPetName(petInfo.getPet_name());
        petEntity.setStore(petInfo.getPet_store());
        petEntity.setSinglePrice(petInfo.getPet_price());
        petEntity.setDescript(petInfo.getDescription());
        petDAO.save(petEntity);
        petDAO.flush();
        return 1;
    }

    @Override
    public PetInfo getPet(int id) {
        PetEntity petEntity = petDAO.getOne(id);
        PetInfo petInfo = new PetInfo();
        petInfo.setId(petEntity.getId());
        petInfo.setCategory_id(petEntity.getCategoryId());
        petInfo.setPet_name(petEntity.getPetName());
        petInfo.setPet_price(petEntity.getSinglePrice());
        petInfo.setPet_store(petEntity.getStore());
        petInfo.setDescription(petEntity.getDescript());
        return petInfo;
    }

    @Override
    public PetInfo getPet(String petName) {
        PetEntity petEntity = petDAO.findPetEntityByPetName(petName);
        PetInfo petInfo = new PetInfo();
        petInfo.setId(petEntity.getId());
        petInfo.setCategory_id(petEntity.getCategoryId());
        petInfo.setPet_name(petEntity.getPetName());
        petInfo.setPet_price(petEntity.getSinglePrice());
        petInfo.setPet_store(petEntity.getStore());
        petInfo.setDescription(petEntity.getDescript());
        return petInfo;
    }

    @Override
    public PetListInfo getPets(String petName) {
        List<PetEntity> petEntities = petDAO.findPetEntitiesByPetNameLike(petName);
        PetListInfo petListInfo = new PetListInfo();
        List<PetInfo> petInfos = new ArrayList<>();
        for (PetEntity petEntity : petEntities) {
            PetInfo petInfo = new PetInfo();
            petInfo.setId(petEntity.getId());
            petInfo.setCategory_id(petEntity.getCategoryId());
            petInfo.setPet_name(petEntity.getPetName());
            petInfo.setPet_price(petEntity.getSinglePrice());
            petInfo.setPet_store(petEntity.getStore());
            petInfo.setDescription(petEntity.getDescript());
            petInfos.add(petInfo);
        }
        petListInfo.setPetInfos(petInfos);
        return petListInfo;
    }

    @Override
    public PetListInfo getPets(int cid) {
        List<PetEntity> petEntities = petDAO.findPetEntitiesByCategoryId(cid);
        PetListInfo petListInfo = new PetListInfo();
        List<PetInfo> petInfos = new ArrayList<>();
        for (PetEntity petEntity : petEntities) {
            PetInfo petInfo = new PetInfo();
            petInfo.setId(petEntity.getId());
            petInfo.setCategory_id(cid);
            petInfo.setPet_name(petEntity.getPetName());
            petInfo.setPet_price(petEntity.getSinglePrice());
            petInfo.setPet_store(petEntity.getStore());
            petInfo.setDescription(petEntity.getDescript());
            petInfos.add(petInfo);
        }
        petListInfo.setPetInfos(petInfos);
        return petListInfo;
    }
}
