package br.com.pedrobelmino.axon.labs.bank.controller;

import br.com.pedrobelmino.axon.labs.bank.command.AddBankAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.command.DebitAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.command.RemoveBankAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.command.CreditAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.dto.BankAccountDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;


@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/bank-accounts")
public class BankAccountController {

    private CommandGateway commandGateway;

    @PostMapping
    public CompletableFuture<String> create(@RequestBody BankAccountDTO dto) {
        AddBankAccountCommand command = new AddBankAccountCommand(UUID.randomUUID().toString(), dto.getName());
        log.info("Executing command: {}", command);
        return commandGateway.send(command);
    }

    @PutMapping("/{id}/credit")
    public CompletableFuture<String> credit(@PathVariable String id, @RequestBody BankAccountDTO dto) {
        CreditAccountCommand command = new CreditAccountCommand(id, dto.getValue());
        log.info("Executing command: {}", command);
        return commandGateway.send(command);
    }

    @PutMapping("/{id}/debit")
    public CompletableFuture<String> debit(@PathVariable String id, @RequestBody BankAccountDTO dto) {
        DebitAccountCommand command = new DebitAccountCommand(id, dto.getValue());
        log.info("Executing command: {}", command);
        return commandGateway.send(command);
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<String> remove(@PathVariable String id) {
        RemoveBankAccountCommand command = new RemoveBankAccountCommand(id);
        log.info("Executing command: {}", command);
        return commandGateway.send(command);
    }
}