package pl.training.bank.service.account;

import lombok.Setter;
import pl.training.bank.api.account.InsufficientFundsException;
import pl.training.bank.entity.Account;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Setter
@Stateless
public class AccountService {

    @EJB
    private AccountNumberGenerator accountNumberGenerator;
    @EJB
    private AccountRepository accountRepository;

    public Account createAccount() {
        String accountNumber = accountNumberGenerator.getNext();
        Account account = new Account(accountNumber);
        accountRepository.save(account);
        return account;
    }

    public void deposit(long funds, String accountNumber) {
       accountRepository.getByNumber(accountNumber).deposit(funds);
    }

    public void withdraw(long funds, String accountNumber) {
        Account account = accountRepository.getByNumber(accountNumber);
        checkFunds(funds, account);
        account.withdraw(funds);
    }

    private void checkFunds(long funds, Account account) {
        if (account.getBalance() < funds) {
            throw new InsufficientFundsException();
        }
    }

    public Account getAccount(String accountNumber) {
        return accountRepository.getByNumber(accountNumber);
    }

}
