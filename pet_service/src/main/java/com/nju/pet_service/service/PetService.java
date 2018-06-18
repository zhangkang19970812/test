package com.nju.pet_service.service;

import com.nju.pet_service.restController.info.PetInfo;
import com.nju.pet_service.restController.info.PetListInfo;

public interface PetService {

    public int addPet(PetInfo petInfo);
    public PetInfo getPet(int id);
    public PetListInfo getPets(String petName);
    public PetListInfo getPets(int cid);
    public PetInfo getPet(String petName);
}
