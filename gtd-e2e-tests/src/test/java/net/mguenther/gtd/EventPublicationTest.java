package net.mguenther.gtd;

import feign.Feign;
import feign.Logger;
import feign.Response;
import feign.httpclient.ApacheHttpClient;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import net.mguenther.gtd.client.GettingThingsDone;
import net.mguenther.gtd.client.StockCreate;
import net.mguenther.gtd.domain.event.StockCreated;
import net.mguenther.gtd.kafka.serialization.AvroStockEvent;
import net.mguenther.gtd.kafka.serialization.StockEventConverter;
import net.mguenther.gtd.kafka.serialization.StockEventDeserializer;
import net.mguenther.kafka.junit.ExternalKafkaCluster;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static net.mguenther.kafka.junit.ObserveKeyValues.on;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Disabled
public class EventPublicationTest {

    private static final String URL = "http://localhost:8765/api";

    private final StockEventConverter converter = new StockEventConverter();

    @Test
    public void anItemCreatedEventShouldBePublishedAfterCreatingNewItem() throws Exception {

        ExternalKafkaCluster kafka = ExternalKafkaCluster.at("http://localhost:9092");
        GettingThingsDone gtd = createGetthingThingsDoneClient();
        String itemId = extractItemId(gtd.createStock(new StockCreate("EVENTID_001", System.currentTimeMillis(), "123.45", "LTI")));

        List<AvroStockEvent> publishedEvents = kafka
                .observeValues(on("topic-getting-things-done", 1, AvroStockEvent.class)
                        .observeFor(10, TimeUnit.SECONDS)
                        .with(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StockEventDeserializer.class)
                        .filterOnKeys(aggregateId -> aggregateId.equals(itemId)));

        StockCreated stockCreatedEvent = publishedEvents.stream()
                .findFirst()
                .map(converter::to)
                .map(e -> (StockCreated) e)
                .orElseThrow(AssertionError::new);

        assertThat(stockCreatedEvent.getCompanyCode(), equalTo(itemId));
        assertThat(stockCreatedEvent.getStockPrice(), equalTo("I gotta do my homework!"));
    }

    private GettingThingsDone createGetthingThingsDoneClient() {
        return Feign.builder()
                .client(new ApacheHttpClient())
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .logger(new Slf4jLogger(GettingThingsDone.class))
                .logLevel(Logger.Level.FULL)
                .target(GettingThingsDone.class, URL);
    }

    private String extractItemId(final Response response) {
        return response.headers()
                .get("Location")
                .stream()
                .findFirst()
                .map(s -> s.replace("/items/", ""))
                .orElseThrow(AssertionError::new);
    }
}
