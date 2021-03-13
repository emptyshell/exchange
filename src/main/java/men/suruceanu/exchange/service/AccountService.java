package men.suruceanu.exchange.service;

import men.suruceanu.exchange.dao.Account;
import men.suruceanu.exchange.dto.exception.AccountNotFoundException;
import men.suruceanu.exchange.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

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

}
