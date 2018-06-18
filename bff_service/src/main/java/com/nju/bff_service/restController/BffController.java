package com.nju.bff_service.restController;

import com.nju.bff_service.loadbalancer.PortRandom;
import com.nju.bff_service.restController.info.*;
import com.nju.bff_service.restController.vo.*;
import com.nju.bff_service.service.BffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/bff")
public class BffController {
    @Autowired
    BffService bffService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BffController.class);

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @GetMapping("/log-instance")
    public void logUserInstance(){
        ServiceInstance serviceInstance = loadBalancerClient.choose("inventory-service");
        BffController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(), serviceInstance.getHost(), serviceInstance.getPort());

    }

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResultVO health(){
        return new ResultVO("health is ok!", 1);
    }

    @RequestMapping(value = "/remote/health", method = RequestMethod.GET)
    public ResultVO remoteHealth(){
        return new ResultVO(bffService.health("http://order-service/cart/health"), 1);
    }

    //register
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResultVO register(@RequestBody UserInfo registerInfo){
        if (bffService.addUser(registerInfo) == 1){
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }

    //login
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResultVO login(@RequestBody LoginInfo loginInfo, HttpSession session){
        int id = bffService.login(loginInfo);
        if (id >= 0){
            session.setAttribute("userId", id);
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }

    //list my orders
    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public OrderListVO getOrders(HttpSession session){
        int uid = (Integer) session.getAttribute("userId");
        return bffService.getOrders(uid);
    }

    //order detail
    @RequestMapping(value = "/order/{oid}", method = RequestMethod.GET)
    public OrderVO getOrder(@PathVariable Integer oid){
        return bffService.getOrder(oid);
    }

    //refund
    @RequestMapping(value = "/refund", method = RequestMethod.POST)
    public ResultVO refund(@RequestBody RefundInfo refundInfo){
        if (bffService.refund(refundInfo) == 1){
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }

    //buy
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public ResultVO buy(@RequestBody OrderInfo orderInfo){
        if (bffService.buy(orderInfo) == 1){
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }

    //list category
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public CategoryListVO getCategory(){
        return bffService.getCategories();
    }

    //list pets
    @RequestMapping(value = "/pets/{cid}", method = RequestMethod.GET)
    public ProductListInfo getPet(@PathVariable Integer cid){
        return bffService.getPets(cid);
    }

    //update my info
    @RequestMapping(value = "/account/update", method = RequestMethod.POST)
    public ResultVO updateUser(@RequestBody UserInfo userInfo){
        if (bffService.addUser(userInfo) == 1){
            return new ResultVO("success", 1);
        }
        return new ResultVO("fail", 0);
    }
}
