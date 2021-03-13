package men.suruceanu.exchange.controller;

import men.suruceanu.exchange.dao.Account;
import men.suruceanu.exchange.dto.exception.AccountNotFoundException;
import men.suruceanu.exchange.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
    public List<Account> getAllAccountes() {
        return accountService.getAllAccount();
    }

    @PostMapping("/account")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Account createAccount(@Valid @RequestBody Account account) {
        return accountService.addAccountInfo(account);
    }

    @PutMapping("/account/{accountId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public Account updateAccount(@PathVariable long accountId, @Valid @RequestBody Account account) throws AccountNotFoundException {
        return accountService.editAccountInfo(accountId, account);
    }

    @DeleteMapping("/account/{accountId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    public void updateAccount(@PathVariable long accountId) {
        accountService.deleteAccount(accountId);
    }
}
