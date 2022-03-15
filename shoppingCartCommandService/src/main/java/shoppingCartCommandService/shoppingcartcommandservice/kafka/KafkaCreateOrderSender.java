package shoppingCartCommandService.shoppingcartcommandservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shoppingCartCommandService.shoppingcartcommandservice.events.CartCheckoutEvent;

@Service
public class KafkaCreateOrderSender {
    @Autowired
    private KafkaTemplate<String, CartCheckoutEvent> kafkaTemplate;

    public void send(String topic, CartCheckoutEvent cartCheckoutEvent) {
        kafkaTemplate.send(topic, cartCheckoutEvent);
    }
}
