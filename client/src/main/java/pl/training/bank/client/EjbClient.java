package pl.training.bank.client;

import pl.training.bank.api.account.AccountNumberGenerator;

import javax.naming.NamingException;

public class EjbClient {

    public static void main(String[] args) throws NamingException {
        ProxyFactory proxyFactory = new ProxyFactory();
        AccountNumberGenerator accountNumberGenerator = proxyFactory.createProxy("java:/bank/IncrementalAccountNumberGenerator!pl.training.bank.api.account.AccountNumberGenerator");
        System.out.println(accountNumberGenerator.getNext());
    }

}
