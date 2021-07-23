package net.mguenther.gtd.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.mguenther.gtd.domain.event.StockCreated;
import net.mguenther.gtd.domain.event.StockDeleted;
import net.mguenther.gtd.domain.event.StockEvent;

import java.util.concurrent.CompletableFuture;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@Service
public class StockUpdater implements EventHandler {

    private static final Logger log = LoggerFactory.getLogger(StockUpdater.class);

    private final StocksRepository repository;

    @Autowired
    public StockUpdater(final StocksRepository repository) {
        this.repository = repository;
    }

    @Override
    public CompletableFuture<Void> onEvent(final StockEvent event) {

        return CompletableFuture.runAsync(() -> {
            if (event instanceof StockCreated) {
                createNewItem((StockCreated) event);
            } else  if (event instanceof StockDeleted) {
            	modifyExistingItem((StockDeleted) event);
            }
        });
    }

    private void createNewItem(final StockCreated stockCreated) {
        final Stock newItem = new Stock(stockCreated);
        repository.save(newItem);
        log.info("Applied event {} and created new item with current state {}.", stockCreated, newItem);
    }

    private void modifyExistingItem(final StockDeleted event) {
    	
        repository.findByCompanyCode(event.getComapnyCode()).ifPresent(stock -> {
             stock.stream().forEachOrdered(s-> repository.delete(s));
            log.info("Applied event {} to the aggregate with ID {}", event, event.getComapnyCode());
        });
    }
}
