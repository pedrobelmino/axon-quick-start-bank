package br.com.pedrobelmino.axon.labs.bank.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@ToString
@AllArgsConstructor
public class RemoveBankAccountCommand {

    @TargetAggregateIdentifier
    private String id;
}
