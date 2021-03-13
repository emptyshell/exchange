package men.suruceanu.exchange.service;

import men.suruceanu.exchange.dao.Exchange;
import men.suruceanu.exchange.dao.ExchangeHistory;
import men.suruceanu.exchange.dto.CursValutar;
import men.suruceanu.exchange.dto.SchimbValutar;
import men.suruceanu.exchange.dto.SchimbValutarEditare;
import men.suruceanu.exchange.dto.exception.*;
import men.suruceanu.exchange.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ExchangeService {

    @Autowired
    private ExchangeRepository exchangeRepository;

    @Autowired
    private CurrencyRepository currencyRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private ExchangeHistoryRepository exchangeHistoryRepository;

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
        existingExchange.setDate(exchange.getDate());

        return exchangeRepository.save(existingExchange);
    }

    public void deleteExchange(Long exchangeId) {
        exchangeRepository.findById(exchangeId).ifPresent(exchange -> exchangeRepository.delete(exchange));
    }

    public List<Exchange> getAllExchange() {
        return exchangeRepository.findAll();
    }

    public Exchange addExchangeRecord(String employeeLogin, CursValutar cursValutar) throws CurrencyNotFoundException, EmployeeNotFoundException {
        if(exchangeRepository.existsByDateAndCurrencyName(new Date(), cursValutar.getCodValuta())) {
            throw new IllegalArgumentException("Today exchange rate is added!");
        }

        Exchange exchange = new Exchange();
        exchange.setTimestamp(LocalDateTime.now());
        exchange.setRate(cursValutar.getRata());
        exchange.setCurrency(currencyRepository.findByName(cursValutar.getCodValuta()).orElseThrow(() -> new CurrencyNotFoundException("This currency is not available in database!")));
        exchange.setSellPrice(cursValutar.getCurs());
        exchange.setBuyPrice(cursValutar.getCurs());
        exchange.setBranchId(employeeRepository.findByEmployeeLogin(employeeLogin).orElseThrow(() -> new EmployeeNotFoundException("This employee is not in database!")).getBranchId().get(0));
        exchange.setDate(new Date());

        return exchangeRepository.save(exchange);
    }

    public Exchange getExchangeRecord(Long branchId, String currencyName) {
        return exchangeRepository.findByBranchIdAndCurrencyName(branchId, currencyName, new Date());
    }

    public SchimbValutar exchangeCurrency(String employeeLogin, Long branchId, SchimbValutar schimbValutar) throws BranchNotFoundException, EmployeeNotFoundException {
        Exchange exchange = exchangeRepository.findByBranchIdAndCurrencyName(branchId, schimbValutar.getCodValuta(), new Date());
        ExchangeHistory exchangeHistory = new ExchangeHistory();
        exchangeHistory.setExchangeId(exchange);
        exchangeHistory.setBranchId(branchRepository.findById(branchId).orElseThrow(() -> new BranchNotFoundException("This branch not available in database!")));
        exchangeHistory.setAmount(schimbValutar.getSumaPrimita());
        exchangeHistory.setTimestamp(LocalDateTime.now());
        exchangeHistory.setEmployeeId(employeeRepository.findByEmployeeLogin(employeeLogin).orElseThrow(() -> new EmployeeNotFoundException("This employee is not in database!")));
        exchangeHistory.setDate(exchange.getDate());

        return convertExchangeHistoryToSchimbValutar(exchangeHistoryRepository.save(exchangeHistory));
    }

    private SchimbValutar convertExchangeHistoryToSchimbValutar(ExchangeHistory exchangeHistory) {
        SchimbValutar schimbValutar = new SchimbValutar();
        double sellPrice = exchangeHistory.getExchangeId().getSellPrice();
        schimbValutar.setCursSchimb(sellPrice);
        schimbValutar.setCodValuta(exchangeHistory.getExchangeId().getCurrency().getName());
        schimbValutar.setUtilizator(exchangeHistory.getEmployeeId().getEmployeeLogin());
        schimbValutar.setSumaPrimita(exchangeHistory.getAmount());
        schimbValutar.setSumaEliberata(exchangeHistory.getAmount() * sellPrice);

        return schimbValutar;
    }
}
