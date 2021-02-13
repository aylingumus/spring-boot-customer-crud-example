package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.domain.AddressDO;
import com.example.demo.domain.CustomerDO;
import com.example.demo.dto.AddressDTO;
import com.example.demo.dto.CustomerDTO;
import com.example.demo.repository.CustomerRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDO createCustomer(CustomerDO customer) {
        return customerRepository.save(customer);
    }

    // @Override
    // public CustomerDO updateCustomer(CustomerDO customer) {
    //     long customerId = customer.getId();
    //     Optional<CustomerDO> currentCustomer = customerRepository.findById(customerId);
    //     if (currentCustomer.isPresent()) {
    //         currentCustomer.get().setName(customer.getName());
    //         currentCustomer.get().setSurname(customer.getSurname());

    //         AddressDO customerAddress = currentCustomer.get().getAddress();
    //         if (customerAddress == null) {
    //             customerAddress = new AddressDO();
    //         }
    //         customerAddress.setCity(customer.getAddress().getCity());
    //         customerAddress.setStreet(customer.getAddress().getStreet());
    //         customerAddress.setZipCode(customer.getAddress().getZipCode());                       

    //         CustomerDO savedCustomer = customerRepository.save(currentCustomer.get());
    //         customerAddress.setId(savedCustomer.getAddress().getId());
    //         currentCustomer.get().setAddress(customerAddress); 

    //         return savedCustomer;
    //     }
    //     return null;
    // }

    @Override
    public CustomerDTO updateCustomer(CustomerDO customer) {
        long customerId = customer.getId();
        Optional<CustomerDO> currentCustomer = customerRepository.findById(customerId);
        if (currentCustomer.isPresent()) {
            currentCustomer.get().setName(customer.getName());
            currentCustomer.get().setSurname(customer.getSurname());

            AddressDO customerAddress = currentCustomer.get().getAddress();
            if (customerAddress == null) {
                customerAddress = new AddressDO();
            }
            customerAddress.setCity(customer.getAddress().getCity());
            customerAddress.setStreet(customer.getAddress().getStreet());
            customerAddress.setZipCode(customer.getAddress().getZipCode());
            currentCustomer.get().setAddress(customerAddress);

            customerRepository.save(currentCustomer.get());

            CustomerDTO customerDTO = new ModelMapper().map(currentCustomer.get(), CustomerDTO.class);
            AddressDTO addressDTO = new ModelMapper().map(customerAddress, AddressDTO.class);
            customerDTO.setAddressDTO(addressDTO);
            return customerDTO;
        }
        return null;
    }

    @Override
    public void deleteCustomer(Long customerId) {
        Optional<CustomerDO> currentCustomer = customerRepository.findById(customerId);
        if (currentCustomer.isPresent()) {
            customerRepository.deleteById(customerId);
        }
    }

    @Override
    public CustomerDO getCustomer(Long customerId) {
        Optional<CustomerDO> currentCustomer = customerRepository.findById(customerId);
        if (currentCustomer.isPresent()) {
            return currentCustomer.get();
        }
        return null;
    }

    @Override
    public List<CustomerDO> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDO getCustomer(String customerUsername) {
        Optional<CustomerDO> currentCustomer = customerRepository.findByUsername(customerUsername);
        if (currentCustomer.isPresent()) {
            return currentCustomer.get();
        }
        return null;
    }

}
