package com.example.demo.controller;

import java.util.List;

import com.example.demo.domain.CustomerDO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    
    @GetMapping("/hello")
    String hello() {
        return "Hello World!";
    }

    @ApiOperation(value = "Creates new customer")
    @PostMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerDO> createCustomer(@RequestBody CustomerDO customerDO) {
        CustomerDO createdCustomer = customerService.createCustomer(customerDO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updates an existing customer")
    @PutMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody CustomerDO customerDO) {
        CustomerDTO updatedCustomer = customerService.updateCustomer(customerDO);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
    }

    // @PutMapping(path = "/customer", consumes = "application/json", produces = "application/json")
    // public ResponseEntity<CustomerDO> updateCustomer(@RequestBody CustomerDO customerDO) {
    //     CustomerDO updatedCustomer = customerService.updateCustomer(customerDO);
    //     return new ResponseEntity<>(updatedCustomer, HttpStatus.CREATED);
    // }

    @ApiOperation(value = "Gets customer by id")
    @GetMapping(path = "/customers/{customerId}")
    public ResponseEntity<CustomerDO> getCustomer(@PathVariable(value = "customerId") Long customerId) {
        CustomerDO customer = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets customer by username")
    @GetMapping(path = "/customers-by-username/{customerUsername}")
    public ResponseEntity<CustomerDO> getCustomer(@PathVariable(value = "customerUsername") String customerUsername) {
        CustomerDO customer = customerService.getCustomer(customerUsername);
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @ApiOperation(value = "Gets all customers")
    @GetMapping(path = "/customers")
    public ResponseEntity<List<CustomerDO>> getAllCustomers() {
        List<CustomerDO> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @ApiOperation(value = "Deletes customer by id")
    @DeleteMapping(path = "/customers/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(value = "customerId") Long customerId) {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>("Customer with id: " + customerId + " is deleted.", HttpStatus.OK);
    }
}
