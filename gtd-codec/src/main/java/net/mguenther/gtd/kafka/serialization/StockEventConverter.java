package net.mguenther.gtd.kafka.serialization;

import net.mguenther.gtd.domain.event.StockCreated;
import net.mguenther.gtd.domain.event.StockDeleted;
import net.mguenther.gtd.domain.event.StockEvent;
import org.springframework.stereotype.Component;

/**
 * Converts bidirectionally between domain events and their respective Avro representation.
 * This is a bit of a mess, but we have to cope with it due to the lack of polymorphy and
 * inheritance in Avro.
 *
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@Component
public class StockEventConverter {
//
    /**
     * Consumes a domain event of type {@code ItemEvent} and returns its corresponding
     * Avro type (cf. {@code AvroItemEvent}).
     *
     * @param event
     *      the domain event that ought to be converted
     * @return
     *      instance of {@code AvroItemEvent} that mirrors the domain event
     */
    public AvroStockEvent from(final StockEvent event) {

        if (event instanceof StockCreated) return from((StockCreated) event);
        else if (event instanceof StockDeleted) return from((StockDeleted) event);
        else throw new IllegalArgumentException("Unsupported event type " + event.getClass());
    }

    private AvroStockEvent from(final StockCreated event) {

        final AvroStockCreated avroEvent = AvroStockCreated.newBuilder()
        		.setComapnyCode(event.getComapnyCode())
        		.setStockPrice(event.getStockPrice())
        		.build();

        return wrap(event, avroEvent);
    }

   
    private AvroStockEvent from(final StockDeleted event) {

        final AvroStockDeleted avroEvent = AvroStockDeleted.newBuilder()
        		.setComapnyCode(event.getComapnyCode())
                .build();

        return wrap(event, avroEvent);
    }

    private AvroStockEvent wrap(final StockEvent event, final Object eventPayload) {

        return AvroStockEvent
                .newBuilder()
                .setEventId(event.getEventId())
                .setTimestamp(event.getTimestamp())
                .setData(eventPayload)
                .build();
    }

    /**
     * Consumes an Avro event of type {@code AvroItemEvent} and returns its corresponding
     * domain event (cf. {@code ItemEvent}).
     *
     * @param event
     *      the Avro event that ought to be converted
     * @return
     *      instance of {@code ItemEvent} that mirrros the Avro event
     */
    public StockEvent to(final AvroStockEvent event) {

        final String eventId = String.valueOf(event.getEventId());
        final long timestamp = event.getTimestamp();

        StockEvent domainEvent = null;

        if (event.getData() instanceof AvroStockCreated) {

            final AvroStockCreated payload = (AvroStockCreated) event.getData();
            domainEvent = new StockCreated(eventId,timestamp,payload.getStockPrice(),payload.getComapnyCode());

        } else if (event.getData() instanceof AvroStockDeleted) {

            final AvroStockDeleted payload = (AvroStockDeleted) event.getData();
            domainEvent = new StockDeleted(eventId, timestamp, payload.getComapnyCode());

        } else {
            throw new IllegalArgumentException("Unsupported event payload for event with ID " + eventId);
        }

        return domainEvent;
    }
}
