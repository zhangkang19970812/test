package com.nju.order_service.service;

import com.nju.order_service.dao.OrderDAO;
import com.nju.order_service.dao.OrderItemDAO;
import com.nju.order_service.model.PetOrderEntity;
import com.nju.order_service.model.PetOrderItemEntity;
import com.nju.order_service.restController.info.OrderInfo;
import com.nju.order_service.restController.info.OrderItemInfo;
import com.nju.order_service.restController.info.RefundInfo;
import com.nju.order_service.restController.vo.OrderItemVO;
import com.nju.order_service.restController.vo.OrderListVO;
import com.nju.order_service.restController.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;
    @Autowired
    OrderItemDAO orderItemDAO;

    public int addOrder(OrderInfo orderInfo){
        Integer orderId = orderInfo.getId();
        if (orderId == -1){
            //add
            orderId = orderDAO.findMaxId();
            if (orderId == null) orderId = 0;
            orderId++;
            List<OrderItemInfo> orderItemInfos = orderInfo.getOrderItems();
            Integer orderItemId = orderItemDAO.findMaxId();
            if (orderItemId == null) orderItemId = 0;
            for (OrderItemInfo ois : orderItemInfos) {
                PetOrderItemEntity petOrderItemEntity = new PetOrderItemEntity();
                petOrderItemEntity.setId(++orderItemId);
                petOrderItemEntity.setOrderId(orderId);
                petOrderItemEntity.setPetName(ois.getPet_name());
                petOrderItemEntity.setPetNum(ois.getPet_num());
                petOrderItemEntity.setSinglePrice(ois.getPrice());
                orderItemDAO.save(petOrderItemEntity);
                orderItemDAO.flush();
            }
            PetOrderEntity petOrderEntity =  new PetOrderEntity();
            petOrderEntity.setId(orderId);
            petOrderEntity.setUserId(orderInfo.getUser_id());
            petOrderEntity.setBill(orderInfo.getBillInfo());
            petOrderEntity.setDescription(orderInfo.getDescription());
            petOrderEntity.setPrice(orderInfo.getPrice());
            petOrderEntity.setStatus(1);
            orderDAO.save(petOrderEntity);
            orderDAO.flush();
            return 1;
        }
        return 0;
    }

    public int refund(RefundInfo refundInfo){
        int id = refundInfo.getOrderId();
        PetOrderEntity petOrderEntity = orderDAO.findOne(id);
        petOrderEntity.setStatus(-1);
        orderDAO.save(petOrderEntity);
        orderDAO.flush();
        return 1;
    }

    public OrderListVO list(Integer uid){
        OrderListVO orderListVO = new OrderListVO();
        List<OrderVO> orderVOS = new ArrayList<>();
        List<PetOrderEntity> orders = orderDAO.findPetOrderEntitiesByUserId(uid);
        for (PetOrderEntity order: orders){
            OrderVO orderVO = getOrder(order.getId());
            orderVOS.add(orderVO);
        }
        orderListVO.setOrders(orderVOS);
        return orderListVO;
    }

    public OrderVO getOrder(Integer oid){
        OrderVO orderVO = new OrderVO();
        PetOrderEntity order = orderDAO.findOne(oid);
        List<PetOrderItemEntity> orderItems = orderItemDAO.findPetOrderItemEntitiesByOrderId(order.getId());
        List<OrderItemVO> orderItemVOS = new ArrayList<>();
        for (PetOrderItemEntity orderItem: orderItems){
            OrderItemVO orderItemVO = new OrderItemVO();
            orderItemVO.setPet_name(orderItem.getPetName());
            orderItemVO.setPet_num(orderItem.getPetNum());
            orderItemVO.setSingle_price(orderItem.getSinglePrice());
            orderItemVOS.add(orderItemVO);
        }
        orderVO.setStatus(order.getStatus());
        orderVO.setBill(order.getBill());
        orderVO.setDescription(order.getDescription());
        orderVO.setPrice(order.getPrice());
        orderVO.setOrderItems(orderItemVOS);
        return orderVO;
    }

}
