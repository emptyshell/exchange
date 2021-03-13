package men.suruceanu.exchange.controller;

import men.suruceanu.exchange.dao.Currency;
import men.suruceanu.exchange.dto.exception.CurrencyNotFoundException;
import men.suruceanu.exchange.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/currency")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<Currency> getAllCurrencyes() {
        return currencyService.getAllCurrency();
    }

    @PostMapping("/currency")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Currency createCurrency(@Valid @RequestBody Currency currency) {
        return currencyService.addCurrencyInfo(currency);
    }

    @PutMapping("/currency/{currencyId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Currency updateCurrency(@PathVariable long currencyId, @Valid @RequestBody Currency currency) throws CurrencyNotFoundException {
        return currencyService.editCurrencyInfo(currencyId, currency);
    }

    @DeleteMapping("/currency/{currencyId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void updateCurrency(@PathVariable long currencyId) {
        currencyService.deleteCurrency(currencyId);
    }
}
