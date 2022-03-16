package edu.miu.service;

import edu.miu.data.CustomerRepository;
import edu.miu.domain.Customer;
import edu.miu.integration.EmailSender;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    private final EmailSender emailSender;

    public CustomerServiceImpl(CustomerRepository customerRepository, EmailSender emailSender) {
        this.customerRepository = customerRepository;
        this.emailSender = emailSender;
    }

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
        emailSender.sendEmail(customer.getEmail(), "Added customer: " + customer.getCustomerId());
    }

    @Override
    public void removeCustomer(Customer customer) {
        String customerId = customer.getCustomerId();
        Customer foundCustomer = customerRepository.findById(customerId).orElse(null);
        if (foundCustomer != null) {
            customerRepository.deleteById(customerId); // .delete(customer);
            emailSender.sendEmail(customer.getEmail(), "Removed customer: " + customerId);
        } else {
            emailSender.sendEmail(customer.getEmail(), "Customer: " + customerId + ", does not exist.");
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        String customerId = customer.getCustomerId();
        Customer foundCustomer = customerRepository.findById(customerId).orElse(null);
        if (foundCustomer != null) {
            customerRepository.save(customer);
            emailSender.sendEmail(customer.getEmail(), "Updated customer: " + customerId);
        } else {
            emailSender.sendEmail(customer.getEmail(), "Customer: " + customerId + ", does not exist.");
        }
    }

    @Override
    public Collection<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }
}
