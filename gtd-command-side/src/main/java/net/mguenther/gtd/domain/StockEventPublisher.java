package net.mguenther.gtd.domain;

import net.mguenther.gtd.domain.event.StockEvent;

/**
 * Publishes a given {@code ItemEvent} to an event log.
 *
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public interface StockEventPublisher {

    void log(StockEvent stockEvent);
}
