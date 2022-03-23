package br.com.pedrobelmino.axon.labs.bank.aggregate;

import br.com.pedrobelmino.axon.labs.bank.command.AddBankAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.command.DebitAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.command.RemoveBankAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.command.CreditAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.event.BankAccountAddedEvent;
import br.com.pedrobelmino.axon.labs.bank.event.BalanceAccountUpdateEvent;
import br.com.pedrobelmino.axon.labs.bank.event.BankAccountRemovedEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import java.math.BigDecimal;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;


@Slf4j
@Getter
@Aggregate
@NoArgsConstructor
public class BankAccountAggregate {

    @AggregateIdentifier
    private String id;
    private String name;
    private BigDecimal balance;
    private BigDecimal value;

    @CommandHandler
    public BankAccountAggregate(AddBankAccountCommand cmd) {
        log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
        Assert.hasLength(cmd.getId(), "Id should not be empty or null.");
        Assert.hasLength(cmd.getName(), "Name should not be empty or null.");

        apply(new BankAccountAddedEvent(cmd.getId(), cmd.getName(), BigDecimal.ZERO));
        log.info("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
    }

    @CommandHandler
    public void handle(CreditAccountCommand cmd) {
        log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
        Assert.hasLength(cmd.getBankId(), "Bank Id should not be empty or null.");
        Assert.notNull(cmd.getValue(), "Value should not be empty or null.");
        BigDecimal newBalance = balance.add(cmd.getValue());
        apply(new BalanceAccountUpdateEvent(cmd.getBankId(), newBalance, cmd.getValue()));
        log.info("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
    }

    @CommandHandler
    public void handle(DebitAccountCommand cmd) {
        log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
        Assert.hasLength(cmd.getBankId(), "Bank Id should not be empty or null.");
        Assert.notNull(cmd.getValue(), "Value should not be empty or null.");
        BigDecimal newBalance = balance.subtract(cmd.getValue());
        Assert.isTrue(newBalance.compareTo(BigDecimal.ZERO)>0, "Saldo insuficiente");
        apply(new BalanceAccountUpdateEvent(cmd.getBankId(), newBalance, cmd.getValue().multiply(BigDecimal.valueOf(-1l))));
        log.info("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
    }

    @CommandHandler
    public void handle(RemoveBankAccountCommand cmd) {
        log.info("Handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
        Assert.hasLength(cmd.getId(), "Id should not be empty or null.");
        apply(new BankAccountRemovedEvent(cmd.getId()));
        log.info("Done handling {} command: {}", cmd.getClass().getSimpleName(), cmd);
    }

    @EventSourcingHandler
    public void on(BankAccountAddedEvent event) {
        log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
        this.id = event.getId();
        this.name = event.getName();
        this.balance = event.getBalance();
        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }

    @EventSourcingHandler
    public void on(BalanceAccountUpdateEvent event) {
        log.info("Handling {} event: {}", event.getClass().getSimpleName(), event);
        this.balance = event.getBalance();
        this.value = event.getValue();
        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }

    @EventSourcingHandler
    public void on(BankAccountRemovedEvent event) {
        log.info("Done handling {} event: {}", event.getClass().getSimpleName(), event);
    }
}