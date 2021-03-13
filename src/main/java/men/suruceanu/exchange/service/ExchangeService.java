package men.suruceanu.exchange.service;

import men.suruceanu.exchange.dao.Exchange;
import men.suruceanu.exchange.dto.exception.ExchangeNotFoundException;
import men.suruceanu.exchange.repositories.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    public Exchange addExchangeInfo(Exchange exchange) {
        return exchangeRepository.save(exchange);
    }

    public Exchange editExchangeInfo(Long exchangeId, Exchange exchange) throws ExchangeNotFoundException {
        Exchange existingExchange = exchangeRepository.findById(exchangeId).orElseThrow(() -> new ExchangeNotFoundException("These exchange is not in the database!"));

        existingExchange.setBranchId(exchange.getBranchId());
        existingExchange.setCurrency(exchange.getCurrency());
        existingExchange.setBuyPrice(exchange.getBuyPrice());
        existingExchange.setRate(exchange.getRate());
        existingExchange.setSellPrice(exchange.getSellPrice());
        existingExchange.setTimestamp(LocalDateTime.now());

        return exchangeRepository.save(existingExchange);
    }

    public void deleteExchange(Long exchangeId) {
        exchangeRepository.findById(exchangeId).ifPresent(exchange -> exchangeRepository.delete(exchange));
    }

    public List<Exchange> getAllExchange() {
        return exchangeRepository.findAll();
    }

}
