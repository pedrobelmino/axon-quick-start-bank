package br.com.pedrobelmino.axon.labs.bank.controller;

import br.com.pedrobelmino.axon.labs.bank.command.AddBankAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.command.RemoveBankAccountCommand;
import br.com.pedrobelmino.axon.labs.bank.command.UpdateBalanceBankAccountCommand;
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

    @PutMapping("/{id}/balances")
    public CompletableFuture<String> updateBalance(@PathVariable String id, @RequestBody BankAccountDTO dto) {
        UpdateBalanceBankAccountCommand command = new UpdateBalanceBankAccountCommand(id, dto.getBalance());
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