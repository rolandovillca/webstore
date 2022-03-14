package edu.miu.service;

import edu.miu.data.CustomerRepository;
import edu.miu.domain.Customer;
import edu.miu.integration.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailSender emailSender;

    @Override
    public void addCustomer(Customer customer) {
        String customerId = customer.getCustomerId();
        customerRepository.save(customer);
        emailSender.sendEmail(customer.getEmail(), "Added customer: " + customerId);
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
    public Collection<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }
}
