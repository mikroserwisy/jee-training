package pl.training.bank.api.account;

import javax.ejb.Remote;

@Remote
public interface AccountNumberGenerator {

    String getNext();

    String getLast();

}
