package com.example.microservice.crypto;

import org.junit.Test;

public class EncryptionTest {

    @Test
    public void encrypt() {
        String s = "abcdefghijklmnopqrstuvwxyz";
        assert (Encryption.decrypt(Encryption.encrypt(s, 1), 1).equals(s));
    }

}