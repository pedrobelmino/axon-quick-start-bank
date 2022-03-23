package br.com.pedrobelmino.axon.labs.bank.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDTO {

    private String name;
    private BigDecimal value;
}
