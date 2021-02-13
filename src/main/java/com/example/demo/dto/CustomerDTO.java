package com.example.demo.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CustomerDTO implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    @JsonIgnore
    private String username;
    private String name;
    private String surname;
    @JsonProperty("address")
    private AddressDTO addressDTO;
}
