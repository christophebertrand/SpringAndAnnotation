package com.example.microservice.crypto;


public class Encryption {


    static String encrypt(String input, int key) {
        return cipher(input,key);
    }
    static String decrypt(String input, int key) {
            return cipher(input, -key);
    }

    private static String cipher(String msg, int shift){
        StringBuilder s = new StringBuilder();
        int len = msg.length();
        for(int x = 0; x < len; x++){
            char c = (char)(msg.charAt(x) + shift);
            if (c > 'z')
                s.append((char) (msg.charAt(x) - (26 - shift)));
            else if (c < 'a')
                s.append((char) (msg.charAt(x) + (26 + shift)));
            else
                s.append((char) (msg.charAt(x) + shift));
        }
        return s.toString();
    }

    static Integer encrypt(Integer i, int key) {
        System.out.println("enc int" + i);
        return i + key;
    }

    static Integer decrypt(Integer i, int key) {
        System.out.println("dec int" + i);
        return i -key;
    }
}
