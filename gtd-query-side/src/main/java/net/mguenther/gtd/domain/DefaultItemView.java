package net.mguenther.gtd.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@Component
public class DefaultItemView implements StocksView {

    private final StocksRepository repository;

    @Autowired
    public DefaultItemView(final StocksRepository repository) {
        this.repository = repository;
    }

    
    
    
    @Override
    public CompletableFuture<List<Stock>> getStocks() {

        return CompletableFuture.supplyAsync(() -> {
            final List<Stock> items = new ArrayList<>(repository.findAll());
            return Collections.unmodifiableList(items);
        });
    }

    @Override
    public CompletableFuture<Optional<Stock>> getStock(final String comapnyCOde) {

        return CompletableFuture.supplyAsync(() -> repository.findById(1l));
    }
}
