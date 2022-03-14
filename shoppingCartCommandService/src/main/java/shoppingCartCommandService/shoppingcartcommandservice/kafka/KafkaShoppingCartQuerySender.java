package shoppingCartCommandService.shoppingcartcommandservice.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shoppingCartCommandService.shoppingcartcommandservice.model.ShoppingCart;

@Service
public class KafkaShoppingCartQuerySender {

    @Autowired
    private KafkaTemplate<String, ShoppingCart> kafkaTemplate;

    public void send(String topic, ShoppingCart shoppingCart) {
        kafkaTemplate.send(topic, shoppingCart);
    }

}
