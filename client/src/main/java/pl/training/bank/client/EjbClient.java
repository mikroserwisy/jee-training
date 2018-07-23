package pl.training.bank.client;

import pl.training.bank.api.Bank;
import pl.training.bank.api.account.AccountDto;
import pl.training.bank.api.account.DispositionDto;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.naming.NamingException;

public class EjbClient {

    private static final String BANK_JNDI_NAME = "java:/bank/BankFacade!pl.training.bank.api.Bank";
    private static final String QUEUE_CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String BANK_QUEUE_JNDI_NAME = "jms/queue/Bank";

    public static void main(String[] args) throws NamingException, InterruptedException {
        ProxyFactory proxyFactory = new ProxyFactory();
        Bank bank = proxyFactory.createProxy(BANK_JNDI_NAME);
        ConnectionFactory connectionFactory = proxyFactory.createProxy(QUEUE_CONNECTION_FACTORY_JNDI_NAME);
        Queue dispositionsQueue = proxyFactory.createProxy(BANK_QUEUE_JNDI_NAME);

        AccountDto accountDto = bank.createAccount();
        DispositionDto dispositionDto = new DispositionDto(accountDto.getNumber(), 1_000, "DEPOSIT");

        try (JMSContext jmsContext = connectionFactory.createContext()) {
            jmsContext.createProducer().send(dispositionsQueue, dispositionDto);
        }

        Thread.sleep(5_000);

        System.out.println(bank.getAccount(accountDto.getNumber()));
    }

}
