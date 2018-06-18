package com.nju.bff_service.restController.info;

import java.util.List;

public class ProductListInfo {
    List<PetItem> productInfos;

    public void setProductInfos(List<PetItem> productInfos) {
        this.productInfos = productInfos;
    }

    public List<PetItem> getProductInfos() {

        return productInfos;
    }
}
