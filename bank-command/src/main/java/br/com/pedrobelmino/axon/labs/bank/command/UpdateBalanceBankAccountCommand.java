package br.com.pedrobelmino.axon.labs.bank.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class UpdateBalanceBankAccountCommand {

    @TargetAggregateIdentifier
    private String bankId;
    private BigDecimal balance;
}
