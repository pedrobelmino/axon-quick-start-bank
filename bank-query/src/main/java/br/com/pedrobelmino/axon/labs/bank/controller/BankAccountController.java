package br.com.pedrobelmino.axon.labs.bank.controller;

import br.com.pedrobelmino.axon.labs.bank.model.BankAccount;
import br.com.pedrobelmino.axon.labs.bank.repository.BankAccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bank-accounts")
public class BankAccountController {

    private BankAccountRepository repository;

    @GetMapping
    public ResponseEntity<Iterable<BankAccount>> getAll() {
        return ResponseEntity.ok(repository.findAll());
    }
}