package shoppingCartCommandService.shoppingcartcommandservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaCreateOrderSender {
    @Autowired
    private KafkaTemplate<String, Integer> kafkaTemplate;

    public void send(String topic, int cartId) {
        kafkaTemplate.send(topic, cartId);
    }
}
