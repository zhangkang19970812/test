package com.nju.order_service.service;

import com.nju.order_service.dao.CartItemDAO;
import com.nju.order_service.model.PetCartItemEntity;
import com.nju.order_service.restController.info.CartCleanInfo;
import com.nju.order_service.restController.info.CartItemInfo;
import com.nju.order_service.restController.info.CartRemoveInfo;
import com.nju.order_service.restController.vo.CartListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    @Autowired
    CartItemDAO cartItemDAO;

    public int addCartItem(CartItemInfo cartItemInfo){
        Integer id = cartItemInfo.getId();
        PetCartItemEntity petCartItemEntity = null;
        if (id == -1){
            id = cartItemDAO.findMaxId();
            if (id == null) id = 0;
            id++;
            petCartItemEntity = new PetCartItemEntity();
            petCartItemEntity.setId(id);
        } else {
            petCartItemEntity = cartItemDAO.findOne(id);
        }
        petCartItemEntity.setPetName(cartItemInfo.getPet_name());
        petCartItemEntity.setPetNum(cartItemInfo.getPet_num());
        petCartItemEntity.setSinglePrice(cartItemInfo.getSingle_price());
        petCartItemEntity.setUserId(cartItemInfo.getUserId());
        cartItemDAO.save(petCartItemEntity);
        cartItemDAO.flush();
        return 1;
    }

    public int removeCartItem(CartRemoveInfo cartRemoveInfo){
        cartItemDAO.delete(cartRemoveInfo.getCartItemId());
        cartItemDAO.flush();
        return 1;
    }

    public int cleanCart(CartCleanInfo cartCleanInfo){
        cartItemDAO.deleteCartItem(cartCleanInfo.getUserId());
        cartItemDAO.flush();
        return 1;
    }

    public CartListVO list(Integer uid){
        List<PetCartItemEntity> petCartItemEntities = cartItemDAO.findPetCartItemEntitiesByUserId(uid);
        CartListVO cartListVO = new CartListVO();
        List<CartItemInfo> cartItemInfos = new ArrayList<>();
        for (PetCartItemEntity petCartItemEntity: petCartItemEntities){
            CartItemInfo cartItemInfo = new CartItemInfo();
            cartItemInfo.setId(petCartItemEntity.getId());
            cartItemInfo.setPet_name(petCartItemEntity.getPetName());
            cartItemInfo.setPet_num(petCartItemEntity.getPetNum());
            cartItemInfo.setSingle_price(petCartItemEntity.getSinglePrice());
            cartItemInfo.setUserId(petCartItemEntity.getUserId());
            cartItemInfos.add(cartItemInfo);
        }
        cartListVO.setCartItemInfos(cartItemInfos);
        return cartListVO;
    }
}
