package net.mguenther.gtd.domain;

import net.mguenther.gtd.domain.event.StockEvent;

import java.util.concurrent.CompletableFuture;

/**
 * Consumes {@code ItemEvent}s and acts upon them.
 *
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public interface EventHandler {

    CompletableFuture<Void> onEvent(StockEvent event);
}
