package com.project.assignment.repository;

import com.project.assignment.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Page<Customer> findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
            String customerId, String accountNumber, String description, Pageable pageable
    );
}
