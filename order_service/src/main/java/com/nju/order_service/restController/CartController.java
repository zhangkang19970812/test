package com.nju.order_service.restController;

import com.nju.order_service.restController.info.CartCleanInfo;
import com.nju.order_service.restController.info.CartItemInfo;
import com.nju.order_service.restController.info.CartRemoveInfo;
import com.nju.order_service.restController.vo.CartListVO;
import com.nju.order_service.restController.vo.ResultVO;
import com.nju.order_service.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartService cartService;
    //add/remove/clean/list

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResultVO health(){
        return new ResultVO("health is ok!", 1);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO add(@RequestBody CartItemInfo cartItemInfo){
        if (cartService.addCartItem(cartItemInfo) == 1){
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.POST)
    public ResultVO remove(@RequestBody CartRemoveInfo cartRemoveInfo){
        if (cartService.removeCartItem(cartRemoveInfo) == 1){
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }

    @RequestMapping(value = "/clean", method = RequestMethod.POST)
    public ResultVO clean(@RequestBody CartCleanInfo cartCleanInfo){
        if (cartService.cleanCart(cartCleanInfo) == 1){
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }

    @RequestMapping(value = "/list/{uid}", method = RequestMethod.GET)
    public CartListVO list(@PathVariable Integer uid){
        return cartService.list(uid);
    }
}
