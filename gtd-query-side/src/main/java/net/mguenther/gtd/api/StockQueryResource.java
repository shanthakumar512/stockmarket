package net.mguenther.gtd.api;

import net.mguenther.gtd.domain.Stock;
import net.mguenther.gtd.domain.StocksView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@RestController
public class StockQueryResource {

    private static final Logger log = LoggerFactory.getLogger(StockQueryResource.class);

    private final StocksView stocksView;

    @Autowired
    public StockQueryResource(final StocksView stocksView) {
        this.stocksView = stocksView;
    }

    @RequestMapping(path = "/stock/getdetail/{companyCode}", method = RequestMethod.GET, produces = "application/json")
    public CompletableFuture<ResponseEntity<Stock>> getStockDetail(@PathVariable("companyCode") String companyCode) {

        log.info("Received a show item request for item with ID {}.", companyCode);

        return stocksView.getStock(companyCode)
                .thenApply(optionalItem -> optionalItem.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    
    @RequestMapping(path = "/stock/getAlldetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompletableFuture<ResponseEntity<List<Stock>>> listAllStocks() {

        log.info("Received a list all managed items request.");

        return stocksView.getStocks()
                .thenApply(ResponseEntity::ok)
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    
    @RequestMapping(path = "/stock/getdetail/{companyCode}/{startDate}/{endDate}", method = RequestMethod.GET, produces = "application/json")
    public CompletableFuture<ResponseEntity<Stock>> getAllStockDetails(@PathVariable("companyCode") String companyCode) {

        log.info("Received a show item request for item with ID {}.", companyCode);

        return stocksView.getStock(companyCode)
                .thenApply(optionalItem -> optionalItem.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build()))
                .exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
    
}
