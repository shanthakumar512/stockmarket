package net.mguenther.gtd.domain;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
public interface StocksView {

    CompletableFuture<List<Stock>> getStocks();
    CompletableFuture<Optional<Stock>> getStock(String companyCode);
}
