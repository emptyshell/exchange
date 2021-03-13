package men.suruceanu.exchange.controller;

import men.suruceanu.exchange.dao.Exchange;
import men.suruceanu.exchange.exception.ExchangeNotFoundException;
import men.suruceanu.exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ExchangeController {

    @Autowired
    private ExchangeService exchangeService;

    @GetMapping("/exchanges")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<Exchange> getAllExchangees() {
        return exchangeService.getAllExchange();
    }

    @PostMapping("/exchange")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Exchange createExchange(@Valid @RequestBody Exchange exchange) {
        return exchangeService.addExchangeInfo(exchange);
    }

    @PutMapping("/exchange/{exchangeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Exchange updateExchange(@PathVariable long exchangeId, @Valid @RequestBody Exchange exchange) throws ExchangeNotFoundException {
        return exchangeService.editExchangeInfo(exchangeId, exchange);
    }

    @DeleteMapping("/exchange/{exchangeId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void updateExchange(@PathVariable long exchangeId) {
        exchangeService.deleteExchange(exchangeId);
    }
}
