package com.project.assignment.service;

import com.project.assignment.entity.Customer;
import com.project.assignment.exception.CustomerNotFoundException;
import com.project.assignment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public Customer updateCustomer(Long id, String description) {
        return customerRepository.findById(id).map(customer -> {
            customer.setDescription(description);
            return customerRepository.save(customer);
        }).orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
    }

    public Page<Customer> getCustomers(String customerId, String accountNumber, String description, Pageable pageable) {
        return customerRepository.findByCustomerIdContainingOrAccountNumberContainingOrDescriptionContaining(
                customerId, accountNumber, description, pageable
        );
    }
}
