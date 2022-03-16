package edu.miu.listener;

import edu.miu.data.CustomerRepository;
import edu.miu.domain.Customer;
import edu.miu.events.OrderEvent;
import edu.miu.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderEmailListener {
    private static final String ORDER_CUSTOMER_TOPIC = "order_placed_topic";

    @Autowired
    private CustomerService customerService;

    @Autowired
    private final CustomerRepository customerRepository;

    public OrderEmailListener(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @KafkaListener(topics = ORDER_CUSTOMER_TOPIC)
    public void receive(@Payload OrderEvent orderEvent) {
        String customerId = orderEvent.getCustomerId();
        String msgEmail = "Customer ID: " + customerId + " was not found for OrderId:" + orderEvent.getOrderId();
        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() ->  new RuntimeException(msgEmail));
        System.out.println("Email sent to customer: "+customerId+", OrderId: "+orderEvent.getOrderId());
    }
}
