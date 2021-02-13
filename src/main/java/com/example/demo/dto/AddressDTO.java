package com.example.demo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class AddressDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private String city;
    private String street;
    private String zipCode;
}
