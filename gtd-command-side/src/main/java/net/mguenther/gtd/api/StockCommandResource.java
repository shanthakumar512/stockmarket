	package net.mguenther.gtd.api;

import net.mguenther.gtd.domain.CommandHandler;
import net.mguenther.gtd.domain.commands.CreateStock;
import net.mguenther.gtd.domain.commands.DeleteStock;
import net.mguenther.gtd.domain.commands.StockCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@RestController

@RequestMapping("/v1.0/market")
public class StockCommandResource {

    private static final Logger log = LoggerFactory.getLogger(StockCommandResource.class);

    private final CommandHandler commandHandler;

    @Autowired
    public StockCommandResource(final CommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RequestMapping(path = "/stock/info/{companyCode}", method = RequestMethod.DELETE, produces = "application/json")
    public CompletableFuture<ResponseEntity<Object>> deleteStock(@PathVariable String companyCode) {

        log.info("Received a delete Stock request for Sotck with CompanyCode {}.", companyCode);
        
        StockCommand deletStockCommand = commandsFor(companyCode);

        return commandHandler
                .onCommand(deletStockCommand)
                .thenApply(dontCare -> ResponseEntity.accepted().build())
                .exceptionally(e -> {
                    log.warn("Caught an exception at the service boundary.", e);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }


    @RequestMapping(path = "/stock/add/", method = RequestMethod.POST, produces = "application/json")
    public CompletableFuture<ResponseEntity<Object>> createStock( @RequestBody CreateStockRequest createStockRequest) {

        log.info("Received a create Stock request for Sotck with CompanyCode {}.", createStockRequest.getCompanyCode());

        
        StockCommand createStockCommand = commandsFor(createStockRequest);
        
        return commandHandler
                .onCommand(createStockCommand)
                .thenApply(dontCare -> ResponseEntity.created(itemUri(createStockCommand.getCompanyCode())).build())
                .exceptionally(e -> {
                    log.warn("Caught an exception at the service boundary.", e);
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                });
    }
    
    private StockCommand commandsFor(final CreateStockRequest createStock) {
        return new CreateStock(createStock.getComapnyStockPrice(),createStock.getCompanyCode());
    }

    private StockCommand commandsFor(final String companyCode) {
        return new DeleteStock(companyCode);
    }
    
    private URI itemUri(final String itemId) {
        return URI.create("/stock/add/" + itemId);
    }
}
