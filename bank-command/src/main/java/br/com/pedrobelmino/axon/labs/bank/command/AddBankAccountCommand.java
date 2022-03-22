package br.com.pedrobelmino.axon.labs.bank.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@ToString
@AllArgsConstructor
public class AddBankAccountCommand {

	@TargetAggregateIdentifier
	private String id;
	private String name;
}