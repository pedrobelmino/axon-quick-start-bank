package br.com.pedrobelmino.axon.labs.bank.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BankAccount {

    @Id
    private String id;
    private String name;
    private BigDecimal balance;
}