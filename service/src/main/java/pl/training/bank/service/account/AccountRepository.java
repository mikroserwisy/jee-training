package pl.training.bank.service.account;

import pl.training.bank.entity.Account;

import javax.ejb.Local;

@Local
public interface AccountRepository {

    Account getById(Long id);

    Account getByNumber(String number);

    void update(Account account);

    Account save(Account account);

}
