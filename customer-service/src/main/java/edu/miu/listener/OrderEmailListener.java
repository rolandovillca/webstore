package edu.miu.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.data.CustomerRepository;
import edu.miu.domain.Customer;
import edu.miu.events.OrderPlacedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderEmailListener {
    private static final String ORDER_CUSTOMER_TOPIC = "order-placed-topic-1";

    @Autowired
    private final CustomerRepository customerRepository;

    public OrderEmailListener(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @KafkaListener(topics = ORDER_CUSTOMER_TOPIC)
    public void receive(@Payload String orderPlacedEventStringFormat) {
        System.out.println("--- ORDER PLACED EVENT RECEIVED ---");
        ObjectMapper objectMapper = new ObjectMapper();
        OrderPlacedEvent orderPlacedEvent = null;
        try {
            orderPlacedEvent = objectMapper.readValue(orderPlacedEventStringFormat, OrderPlacedEvent.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String customerId = orderPlacedEvent.getCustomerId();
        String msgEmail = "Customer ID: " + customerId + " was not found for OrderId:" + orderPlacedEvent.getOrderId();
        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() -> new RuntimeException(msgEmail));
        System.out.println("Email sent to customer: " + customer.getFirstName() + " , " + customer.getEmail() + ", OrderId: " + orderPlacedEvent.getOrderId());
    }
}
