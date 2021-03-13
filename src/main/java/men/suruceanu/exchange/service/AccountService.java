package men.suruceanu.exchange.service;

import men.suruceanu.exchange.dao.Account;
import men.suruceanu.exchange.dto.SchimbValutarEditare;
import men.suruceanu.exchange.dto.exception.AccountNotFoundException;
import men.suruceanu.exchange.dto.exception.CurrencyNotFoundException;
import men.suruceanu.exchange.dto.exception.ExchangeHistoryNotFoundException;
import men.suruceanu.exchange.repositories.AccountRepository;
import men.suruceanu.exchange.repositories.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CurrencyRepository currencyRepository;

    public Account addAccountInfo(Account account) {
        return accountRepository.save(account);
    }

    public Account editAccountInfo(Long branchId, Account branch) throws AccountNotFoundException {
        Account existingAccount = accountRepository.findById(branchId).orElseThrow(() -> new AccountNotFoundException("These account is not in the database!"));

        existingAccount.setAccountAmount(branch.getAccountAmount());
        existingAccount.setBranchId(branch.getBranchId());
        existingAccount.setTimestamp(LocalDateTime.now());
        existingAccount.setCurrencyId(branch.getCurrencyId());

        return accountRepository.save(existingAccount);
    }

    public void deleteAccount(Long accountId) {
        accountRepository.findById(accountId).ifPresent(branch -> accountRepository.delete(branch));
    }

    public List<Account> getAllAccount() {
        return accountRepository.findAll();
    }

    public Account updateExchangedAccountRecord(SchimbValutarEditare schimbValutarEditare) throws ExchangeHistoryNotFoundException, CurrencyNotFoundException {
        Account account = accountRepository.findByCurrencyNameAndTimestamp(schimbValutarEditare.getCodValuta(), schimbValutarEditare.getData())
                .orElseThrow(() -> new ExchangeHistoryNotFoundException("This exchange history is not available in database!"));
        account.setAccountAmount(schimbValutarEditare.getSuma());
        account.setCurrencyId(currencyRepository.findByName(schimbValutarEditare.getCodValuta()).orElseThrow(() -> new CurrencyNotFoundException("This currency is not available in database!")));
        account.setTimestamp(LocalDateTime.now());
        return accountRepository.save(account);
    }

}
