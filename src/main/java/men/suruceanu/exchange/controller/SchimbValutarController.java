package men.suruceanu.exchange.controller;

import men.suruceanu.exchange.dao.*;
import men.suruceanu.exchange.dto.CursValutar;
import men.suruceanu.exchange.dto.SchimbValutar;
import men.suruceanu.exchange.dto.SchimbValutarEditare;
import men.suruceanu.exchange.exception.*;
import men.suruceanu.exchange.repositories.EmployeeRepository;
import men.suruceanu.exchange.service.AccountService;
import men.suruceanu.exchange.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class SchimbValutarController {

    @Autowired
    private ExchangeService exchangeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AccountService accountService;

    @GetMapping("/cursValutar/{currancyName}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public Exchange getExchangeByCurrencyName(@AuthenticationPrincipal final UserDetails user, @PathVariable String currancyName) throws EmployeeNotFoundException {
        Branch branch = employeeRepository.findByEmployeeLogin(user.getUsername()).orElseThrow(() -> new EmployeeNotFoundException("This employee is not in database!")).getBranchId().get(0);
        return exchangeService.getExchangeRecord(branch.getBranchId(), currancyName);
    }

    @PostMapping("/cursValutar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public Exchange createExchange(@AuthenticationPrincipal final UserDetails user, @Valid @RequestBody CursValutar cursValutar) throws CurrencyNotFoundException, EmployeeNotFoundException {
        return exchangeService.addExchangeRecord(user.getUsername(), cursValutar);
    }

    @PostMapping("/schimbValutar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public SchimbValutar buyCurrency(@AuthenticationPrincipal final UserDetails user, @Valid @RequestBody SchimbValutar schimbValutar) throws EmployeeNotFoundException, BranchNotFoundException {
        Branch branch = employeeRepository.findByEmployeeLogin(user.getUsername()).orElseThrow(() -> new EmployeeNotFoundException("This employee is not in database!")).getBranchId().get(0);
        return exchangeService.exchangeCurrency(user.getUsername(), branch.getBranchId(), schimbValutar);
    }

    @PutMapping("/schimbValutar")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public Account updateExchange(@Valid @RequestBody SchimbValutarEditare schimbValutarEditare) throws ExchangeHistoryNotFoundException, CurrencyNotFoundException {
        return accountService.updateExchangedAccountRecord(schimbValutarEditare);
    }
}
