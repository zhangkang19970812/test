package com.nju.bff_service.loadbalancer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;


import java.util.HashMap;
import java.util.List;

public class PortMap {
    @Autowired
    private DiscoveryClient discoveryClient;

    private HashMap<String, Integer> portWeightMap = new HashMap<>();

    public HashMap<String, Integer> getPortMap() {
        List<ServiceInstance> list = discoveryClient.getInstances("INVENTORY-SERVICE");
        int i = 0;
        for (ServiceInstance serviceInstance : list) {
            portWeightMap.put(String.valueOf(serviceInstance.getPort()), ++i);
        }

        return portWeightMap;
    }

}
