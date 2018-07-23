package pl.training.bank.api;

import pl.training.bank.api.account.DispositionDto;

import javax.ejb.Remote;

@Remote
public interface DispositionsCart {

    void add(DispositionDto dispositionDto);

    void submit();

    void cancel();

}
