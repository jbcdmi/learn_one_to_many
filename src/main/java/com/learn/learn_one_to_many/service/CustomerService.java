package com.learn.learn_one_to_many.service;

import com.learn.learn_one_to_many.entity.Customer;
import com.learn.learn_one_to_many.exception.ResourceNotFoundException;
import com.learn.learn_one_to_many.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    final private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer : id =" + id));
    }

    public void deleteCustomerById(Long id) {
        customerRepository.deleteById(id);
    }
}
