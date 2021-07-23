package net.mguenther.gtd.kafka;

import net.mguenther.gtd.kafka.serialization.AvroStockEvent;
import net.mguenther.gtd.kafka.serialization.StockEventSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@Configuration
public class TransactionalItemEventPublisherConfig {

    @Bean
    public ProducerFactory<String, AvroStockEvent> producerFactory() {
        final Map<String, Object> config = new HashMap<>();
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StockEventSerializer.class);
        config.put(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, 1);
        config.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
        config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        config.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "getting-things-done");
        final DefaultKafkaProducerFactory<String, AvroStockEvent> factory =
                new DefaultKafkaProducerFactory<>(config);
        factory.setTransactionIdPrefix("getting-things-done");
        return factory;
    }

    @Bean
    public KafkaTemplate<String, AvroStockEvent> kafkaTemplate(@Autowired ProducerFactory<String, AvroStockEvent> factory) {
        return new KafkaTemplate<>(factory);
    }
}
