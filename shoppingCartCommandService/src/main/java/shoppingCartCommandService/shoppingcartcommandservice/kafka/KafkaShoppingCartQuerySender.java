package shoppingCartCommandService.shoppingcartcommandservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shoppingCartCommandService.shoppingcartcommandservice.model.ShoppingCart;

@Service
public class KafkaShoppingCartQuerySender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, ShoppingCart shoppingCart) {
        ObjectMapper objectMapper = new ObjectMapper();
        String senderMsg = "";
        try {
            senderMsg = objectMapper.writeValueAsString(shoppingCart);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topic, senderMsg);
    }

}
