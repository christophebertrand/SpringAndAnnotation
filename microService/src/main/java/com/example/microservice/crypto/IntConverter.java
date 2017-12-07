package com.example.microservice.crypto;

import javax.persistence.AttributeConverter;

//@Converter(autoApply = true)
public class IntConverter implements AttributeConverter<Integer, Integer>{
    public IntConverter() {
        System.out.println("Starting IntConverter");
    }
    @Override
    public Integer convertToDatabaseColumn(Integer attribute) {
        return attribute + 10;
    }

    @Override
    public Integer convertToEntityAttribute(Integer dbData) {
        return dbData - 10;
    }
}
