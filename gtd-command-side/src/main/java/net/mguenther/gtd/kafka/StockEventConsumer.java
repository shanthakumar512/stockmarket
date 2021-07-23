package net.mguenther.gtd.kafka;

import net.mguenther.gtd.domain.EventHandler;
import net.mguenther.gtd.domain.event.StockEvent;
import net.mguenther.gtd.kafka.serialization.AvroStockEvent;
import net.mguenther.gtd.kafka.serialization.StockEventConverter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * Consumes {@code AvroItemEvent}s that update the internal state of domain for the command-side. This is
 * essential so that the validation that a {@code CommandHandler} performs always goes against the most
 * recent state.
 *
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@Component
public class StockEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(StockEventConsumer.class);

    private final StockEventConverter converter;

    private final EventHandler eventHandler;

    @Autowired
    public StockEventConsumer(final StockEventConverter converter,
                             final EventHandler eventHandler) {
        this.converter = converter;
        this.eventHandler = eventHandler;
    }

    @KafkaListener(topics = "${gtd.topic}", groupId = "${gtd.groupId}")
    public void consume(final AvroStockEvent itemEvent, final Acknowledgment ack) {
        final StockEvent event = converter.to(itemEvent);
        log.debug("Received event {}. Trying to apply it to the latest state of aggregate with ID {}.", event, event.getComapnyCode());
        try {
            eventHandler
                    .onEvent(event)
                    .thenRun(ack::acknowledge);
        } catch (Exception e) {
            // log the exception and do *not* acknowledge the event
            log.warn("Unable to apply event {} to the latest state of aggregate with ID {}.", event, event.getComapnyCode(), e);
        }
    }
}
