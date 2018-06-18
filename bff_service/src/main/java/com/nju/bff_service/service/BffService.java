package com.nju.bff_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nju.bff_service.restController.info.*;
import com.nju.bff_service.restController.vo.*;
import com.nju.bff_service.util.HttpUtil;
import com.nju.bff_service.util.URLConstant;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

@Service
public class BffService {

    @Autowired
    private RestTemplate restTemplate;

    public String health(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //register
    public int addUser(UserInfo registerInfo) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResultVO resultVO = null;
        try {
            String s = mapper.writeValueAsString(registerInfo);
            HttpEntity<String> r = new HttpEntity<>(s, headers);
            resultVO = restTemplate.postForObject(URLConstant.url_add_user, r, ResultVO.class);
            if (resultVO.getResult() == 0) return 0;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    //login
    public int login(LoginInfo loginInfo) {
        //return id
        try {
            UserInfo userInfo = restTemplate.getForObject(URLConstant.url_get_user + loginInfo.getUsername(), UserInfo.class);
            if (userInfo == null || !userInfo.getPassword().equals(loginInfo.getPassword())) return -1;
            return userInfo.getId();
        } catch (Exception e) {
            return -1;
        }

    }

    //get order list
    public OrderListVO getOrders(int uid) {
        try {
            return restTemplate.getForObject(URLConstant.url_get_orders + uid, OrderListVO.class);
        } catch (Exception e) {
            return null;
        }
    }

    //get order
    public OrderVO getOrder(int oid) {
        try {
            return restTemplate.getForObject(URLConstant.url_get_order + oid, OrderVO.class);
        } catch (Exception e) {
            return null;
        }
    }

    //refund
    public int refund(RefundInfo refundInfo) {
        //refund order
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResultVO resultVO = null;
        try {
            String refundString = mapper.writeValueAsString(refundInfo);
            HttpEntity<String> r = new HttpEntity<>(refundString, headers);
            resultVO = restTemplate.postForObject(URLConstant.url_refund_order, r, ResultVO.class);
            if (resultVO.getResult() == 0) return 0;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return 0;
        }

        //update pet
        OrderVO orderVO = restTemplate.getForObject(URLConstant.url_get_order + refundInfo.getOrderId(), OrderVO.class);
        for (OrderItemVO orderItemVO : orderVO.getOrderItems()) {
            try {
                PetItem petItem = restTemplate.getForObject(URLConstant.url_get_pet + orderItemVO.getPet_name(), PetItem.class);
                int num = petItem.getPet_store();
                petItem.setPet_store(num + orderItemVO.getPet_num());
                String petString = mapper.writeValueAsString(petItem);
                HttpEntity<String> r = new HttpEntity<>(petString, headers);
                resultVO = restTemplate.postForObject(URLConstant.url_update_pet, r, ResultVO.class);
                if (resultVO.getResult() == 0) return 0;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 1;
    }

    //buy
    public int buy(OrderInfo orderInfo) {
        //add order
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResultVO resultVO = null;
        try {
            String orderString = mapper.writeValueAsString(orderInfo);
            HttpEntity<String> r = new HttpEntity<>(orderString, headers);
            resultVO = restTemplate.postForObject(URLConstant.url_buy_order, r, ResultVO.class);
            if (resultVO.getResult() == 0) return 0;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return 0;
        }

        //update pet
        for (OrderItemInfo orderItemInfo : orderInfo.getOrderItems()) {
            try {
                PetItem petItem = restTemplate.getForObject(URLConstant.url_get_pet + orderItemInfo.getPet_name(), PetItem.class);
                int num = petItem.getPet_store();
                if (orderItemInfo.getPet_num() > num) return 0;
                petItem.setPet_store(num - orderItemInfo.getPet_num());
                String petString = mapper.writeValueAsString(petItem);
                HttpEntity<String> r = new HttpEntity<>(petString, headers);
                resultVO = restTemplate.postForObject(URLConstant.url_update_pet, r, ResultVO.class);
                if (resultVO.getResult() == 0) return 0;
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 1;
    }

    //getCategories
    public CategoryListVO getCategories() {
        return restTemplate.getForObject(URLConstant.url_get_category, CategoryListVO.class);
    }

    //getPets
    public ProductListInfo getPets(int cid) {
        try {
            return restTemplate.getForObject(URLConstant.url_get_pets + cid, ProductListInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getResponse(InputStream in) throws IOException {
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

//    public static void main(String[] args) {
//        BffService service = new BffService();
//        UserInfo userInfo = new UserInfo();
//        userInfo.setId(-1);
//        userInfo.setUsername("cr");
//        userInfo.setPassword("pwd");
//        userInfo.setEmail("1@cr.com");
//        service.addUser(userInfo);
//    }
}
