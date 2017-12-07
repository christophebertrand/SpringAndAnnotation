package com.example.microservice.crypto;

import javax.persistence.AttributeConverter;

//@Converter(autoApply = true)
public class StringConverter implements AttributeConverter<String, String> {

    public StringConverter() {
        System.out.println("Start StringConverter");
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        System.out.println("enc string");
        return Encryption.encrypt(attribute, 10);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        System.out.println("dec string");
        return Encryption.decrypt(dbData, 10);
    }
}
