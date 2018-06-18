package com.nju.order_service.restController.vo;

import com.nju.order_service.restController.info.CartItemInfo;

import java.util.List;

public class CartListVO {
    List<CartItemInfo> cartItemInfos;

    public void setCartItemInfos(List<CartItemInfo> cartItemInfos) {
        this.cartItemInfos = cartItemInfos;
    }

    public List<CartItemInfo> getCartItemInfos() {

        return cartItemInfos;
    }
}
