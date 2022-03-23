package br.com.pedrobelmino.axon.labs.bank.components;

import br.com.pedrobelmino.axon.labs.bank.event.BankAccountAddedEvent;
import br.com.pedrobelmino.axon.labs.bank.event.BalanceAccountUpdateEvent;
import br.com.pedrobelmino.axon.labs.bank.event.BankAccountRemovedEvent;
import br.com.pedrobelmino.axon.labs.bank.exception.BankAccountNotFoundException;
import br.com.pedrobelmino.axon.labs.bank.model.BankAccount;
import br.com.pedrobelmino.axon.labs.bank.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class BankAccountEventProcessor {

    private final BankAccountRepository repository;

    @EventHandler
    public void on(BankAccountAddedEvent event) {
        BankAccount bankAccount = repository.save(new BankAccount(event.getId(), event.getName(), event.getBalance()));
        log.info("A bank account was added! {}", bankAccount );
    }

    @EventHandler
    public void on(BalanceAccountUpdateEvent event) {
        BankAccount bank = repository.findById(event.getBankId())
                .orElseThrow(BankAccountNotFoundException::new);
        bank.setBalance(event.getBalance());
        repository.save(bank);
        log.info("A bank account balance was updated! {}", bank);
    }

    @EventHandler
    public void on(BankAccountRemovedEvent event) {
        repository.deleteById(event.getId());
        log.info("A bank account was removed! {}", event.getId());
    }
}
