package shoppingCartCommandService.shoppingcartcommandservice.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author kojusujan1111@gmail.com 14/03/22
 */
@Configuration
public class KafkaConfigs {

    @Bean
    public NewTopic topic() {
        return new NewTopic("shopping_cart_query_topic", 1, (short) 1);
    }

}
