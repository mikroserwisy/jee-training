package pl.training.bank.service;

import lombok.Setter;
import lombok.extern.java.Log;
import pl.training.bank.api.Bank;
import pl.training.bank.api.account.AccountDto;
import pl.training.bank.entity.Account;
import pl.training.bank.service.account.AccountService;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;

@Log
@Setter
@Stateless
public class BankFacade implements Bank {

    @EJB
    private AccountService accountService;
    @EJB
    private Mapper mapper;

    @Override
    public AccountDto createAccount() {
        Account account = accountService.createAccount();
        return mapper.map(account, AccountDto.class);
    }

    @Override
    public AccountDto getAccount(String accountNumber) {
        Account account = accountService.getAccount(accountNumber);
        return mapper.map(account, AccountDto.class);
    }

    @PostConstruct
    public void init() {
        log.info("BankFacade is ready...");
    }

    @PreDestroy
    public void destroy() {
        log.info("BankFacade is going down...");
    }

}
