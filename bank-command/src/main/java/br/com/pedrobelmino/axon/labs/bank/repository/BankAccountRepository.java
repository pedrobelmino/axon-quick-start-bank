package br.com.pedrobelmino.axon.labs.bank.repository;


import br.com.pedrobelmino.axon.labs.bank.model.BankAccount;
import org.springframework.data.repository.CrudRepository;

public interface BankAccountRepository extends CrudRepository<BankAccount, String> {
}
