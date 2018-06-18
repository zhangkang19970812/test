package com.nju.order_service.restController;

import com.nju.order_service.restController.info.OrderInfo;
import com.nju.order_service.restController.info.RefundInfo;
import com.nju.order_service.restController.vo.OrderListVO;
import com.nju.order_service.restController.vo.OrderVO;
import com.nju.order_service.restController.vo.ResultVO;
import com.nju.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    //add/refund/update/list/detail

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO add(@RequestBody OrderInfo orderInfo){
        if (orderService.addOrder(orderInfo) == 1){
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }

    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public ResultVO refund(@RequestBody RefundInfo refundInfo){
        if (orderService.refund(refundInfo) == 1){
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }

    @RequestMapping(value = "/list/{uid}", method = RequestMethod.GET)
    public OrderListVO list(@PathVariable Integer uid){
        return orderService.list(uid);
    }

    @RequestMapping(value = "/detail/{oid}", method = RequestMethod.GET)
    public OrderVO get(@PathVariable Integer oid){
        return orderService.getOrder(oid);
    }
}
