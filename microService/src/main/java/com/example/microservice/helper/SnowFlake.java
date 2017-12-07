package com.example.microservice.helper;

import java.util.Random;
public class SnowFlake {

    private static Random r = new Random();

    static public Long getId() {
        return Math.abs(r.nextLong());
    }
}
