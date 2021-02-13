package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.CustomerDO;
import com.example.demo.dto.CustomerDTO;

public interface CustomerService {
    
    CustomerDO createCustomer(CustomerDO customer);
    // CustomerDO updateCustomer(CustomerDO customer);
    CustomerDTO updateCustomer(CustomerDO customer);
    void deleteCustomer(Long customerId);
    CustomerDO getCustomer(Long customerId);
    CustomerDO getCustomer(String customerUsername);
    List<CustomerDO> getAllCustomers();
}
