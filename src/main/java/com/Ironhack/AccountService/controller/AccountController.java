package com.Ironhack.AccountService.controller;

import com.Ironhack.AccountService.dao.Account;
import com.Ironhack.AccountService.dto.accounts.*;
import com.Ironhack.AccountService.enums.Status;
import com.Ironhack.AccountService.proxy.UserRepositoryProxy;
import com.Ironhack.AccountService.service.IAccountService;
import com.Ironhack.AccountService.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepositoryProxy userRepository;

    @Autowired
    private IAccountService accountService;

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<Account> findAll() {
        return accountRepository.findAll();
    };

    @GetMapping("/accounts/byid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Account findById(@PathVariable("id") Long id) {
        Optional<Account> foundAccount = accountRepository.findById(id);
        return (foundAccount.isPresent() ? foundAccount.get() : null);
    }

    @PostMapping("/accounts/new/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createNewAccount(@RequestBody @Valid CheckingAccountDTO account) {
        return accountService.createNewCheckingAccount(account);
    }

    @PostMapping("/accounts/new/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createNewAccount(@RequestBody @Valid SavingsAccountDTO account) {
        return accountService.createNewSavingsAccount(account);
    }

    @PostMapping("/accounts/new/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDTO createNewAccount(@RequestBody @Valid CreditCardAccountDTO account) {
        return accountService.createNewCreditCardAccount(account);
    }

    @PostMapping("/accounts/new/thirdparty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdPartyAccountDTO createNewAccount(@RequestBody @Valid ThirdPartyAccountDTO account) {
        return accountService.createNewThirdPartyAccount(account);
    }

    @PatchMapping("/accounts/update/status/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Status updateStatus(@PathVariable("id") long id) {
        return accountService.updateStatus(id);
    }
}
