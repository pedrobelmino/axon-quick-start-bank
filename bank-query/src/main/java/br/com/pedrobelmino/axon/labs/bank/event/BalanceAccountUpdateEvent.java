package br.com.pedrobelmino.axon.labs.bank.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@ToString
@AllArgsConstructor
public class BalanceAccountUpdateEvent {

	private String bankId;
	private BigDecimal balance;
	private BigDecimal value;
}