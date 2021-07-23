package net.mguenther.gtd.domain;

import net.mguenther.gtd.domain.commands.CreateStock;
import net.mguenther.gtd.domain.commands.DeleteStock;
import net.mguenther.gtd.domain.commands.StockCommand;
import net.mguenther.gtd.domain.event.StockCreated;
import net.mguenther.gtd.domain.event.StockDeleted;
import net.mguenther.gtd.domain.event.StockEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;

/**
 * @author Markus Günther (markus.guenther@gmail.com)
 * @author Boris Fresow (bfresow@gmail.com)
 */
@Service
public class StockManager implements CommandHandler, EventHandler {

	private static final Logger log = LoggerFactory.getLogger(StockManager.class);

	private final Map<String, List<Stock>> items = new ConcurrentHashMap<>();

	private final StockEventPublisher publisher;

	public StockManager(final StockEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public CompletableFuture<Void> onCommand(final StockCommand command) {

		return CompletableFuture.runAsync(() -> validate(command).forEach(publisher::log));
	}

	@Override
	public CompletableFuture<Void> onCommand(final List<StockCommand> commands) {

		return CompletableFuture.runAsync(
				() -> commands.stream().flatMap(command -> validate(command).stream()).forEach(publisher::log));
	}

	private List<StockEvent> validate(final StockCommand command) {

		if (command instanceof CreateStock) {
			return validate((CreateStock) command);
		}
		if (command instanceof DeleteStock) {
			return validate((DeleteStock) command);
		}

		Stock stock = items.get(command.getCompanyCode()).get(0);

		if (stock == null) {
			log.warn("Item with ID {} does not exist but has received a command ({}). This is probably due to a "
					+ "out-of-order arrival of events, which should not happen due to Kafka's topic ordering guarantees. "
					+ "Please check your Kafka topic configuration. The command will be dropped.",
					command.getCompanyCode(), command);
			return emptyList();
		}
		
		else
			return emptyList();
	}

	private List<StockEvent> validate(final CreateStock command) {
		
		return singletonList(new StockCreated(UUID.randomUUID().toString().substring(0, 7), System.currentTimeMillis(),
				command.getStockPrice(), command.getCompanyCode()));
	}

	
	private List<StockEvent> validate(final DeleteStock command) {
		if(items.containsKey(command.getCompanyCode())){
			return emptyList();
		} else {
		return singletonList(new StockDeleted(command.getCompanyCode()));
		}
	}

	private void logValidationFailed(final Stock currentState, final StockCommand command) {
		log.warn("Received command {} which failed to validate against the current state of aggregate {}. "
				+ "Skipping this command.", command, currentState);
	}

	@Override
	public CompletableFuture<Void> onEvent(final StockEvent event) {

		return CompletableFuture.runAsync(() -> {

			if (event instanceof StockCreated) {
				createNewStock((StockCreated) event);
			} else if (event instanceof StockDeleted) {
				deleteStockDetails((StockDeleted)event);
			}
		});
	}

	private void createNewStock(final StockCreated event) {
		final Stock newStock = new Stock(event);
		List<Stock> stocks = new ArrayList<>();
		if (items.containsKey(newStock.getCompanyCode())) {
			stocks = items.get(newStock.getCompanyCode());
			stocks.add(newStock);
			items.put(newStock.getCompanyCode(), stocks);
		} else {
			stocks.add(newStock);
			items.put(newStock.getCompanyCode(), stocks);
		}
	}

	private void deleteStockDetails(final StockEvent event) {
		final Stock currentState= new Stock();
		
		currentState.setCompanyCode(event.getComapnyCode());
		
		List<Stock> stocks = new ArrayList<>();
		stocks.add(currentState);
		currentState.project(event);
        items.put(currentState.getCompanyCode(), stocks);
	}
}
