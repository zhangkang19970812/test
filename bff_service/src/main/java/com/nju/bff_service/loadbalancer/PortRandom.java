package com.nju.bff_service.loadbalancer;

import java.util.*;

public class PortRandom {
    public static String getPort(HashMap<String, Integer> map) {
        Map<String, Integer> portMap = new HashMap<>();
        portMap.putAll(map);

        Set<String> keySet = portMap.keySet();
        ArrayList<String> keyList = new ArrayList<>();
        keyList.addAll(keySet);

        Random random = new Random();
        int randomPos = random.nextInt(keyList.size());

        return keyList.get(randomPos);
    }
}
