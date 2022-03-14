package edu.miu.service;

import edu.miu.domain.Customer;

import java.util.Collection;

public interface CustomerService {
    void addCustomer(Customer customer);
    void removeCustomer(Customer customer);
    void updateCustomer(Customer customer);
    Collection<Customer> findAllCustomers();
}
