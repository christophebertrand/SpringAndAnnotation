package com.example.microservice.helper;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;

@Service
public class KeyServer {


    private Random r = new Random();
    private HashMap<Long, Integer> map;

    public  KeyServer() {
        map = new HashMap<>();
    }

   public Integer insert(Long l) {
       Integer i = r.nextInt();
       i = i % 26;
       map.put(l,i);
       return i;
   }

   public Integer get(Long l) {
       return map.get(l);
   }


}
