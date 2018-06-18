package com.nju.pet_service.restController.info;

import java.util.List;

public class PetListInfo {
    List<PetInfo> petInfos;

    public void setPetInfos(List<PetInfo> petInfos) {
        this.petInfos = petInfos;
    }

    public List<PetInfo> getPetInfos() {

        return petInfos;
    }
}
