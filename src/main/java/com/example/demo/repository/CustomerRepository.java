package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.domain.CustomerDO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDO, Long> {
    Optional<CustomerDO> findByUsername(String username);
}
