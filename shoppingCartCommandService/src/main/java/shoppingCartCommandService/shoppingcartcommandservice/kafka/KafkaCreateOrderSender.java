package shoppingCartCommandService.shoppingcartcommandservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import shoppingCartCommandService.shoppingcartcommandservice.events.CartCheckoutEvent;

@Service
public class KafkaCreateOrderSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String topic, CartCheckoutEvent cartCheckoutEvent) {
        ObjectMapper mapper = new ObjectMapper();
        String senderMsg = "";
        try {
            senderMsg = mapper.writeValueAsString(cartCheckoutEvent);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topic, senderMsg);
    }
}
