package com.example.microservice;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@EqualsAndHashCode
@ToString
@Getter
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

//    @Convert(converter = StringConverter.class)
    private String firstName;

    @Type(type ="com.example.microservice.crypto.EncryptedValue")
    private String lastName;

    @Type(type ="com.example.microservice.crypto.EncryptedValue")
    private Integer age;


}
