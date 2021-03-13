package men.suruceanu.exchange.service;


import men.suruceanu.exchange.dao.Currency;
import men.suruceanu.exchange.dto.exception.CurrencyNotFoundException;
import men.suruceanu.exchange.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Currency addCurrencyInfo(Currency currency) {
        return currencyRepository.save(currency);
    }

    public Currency editCurrencyInfo(Long currencyId, Currency currency) throws CurrencyNotFoundException {
        Currency existingCurrency = currencyRepository.findById(currencyId).orElseThrow(() -> new CurrencyNotFoundException("These currency is not in the database!"));

        existingCurrency.setName(currency.getName());
        existingCurrency.setFullName(currency.getFullName());

        return currencyRepository.save(existingCurrency);
    }

    public void deleteCurrency(Long currencyId) {
        currencyRepository.findById(currencyId).ifPresent(currency -> currencyRepository.delete(currency));
    }

    public List<Currency> getAllCurrency() {
        return currencyRepository.findAll();
    }

}
