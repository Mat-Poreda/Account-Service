package com.Ironhack.AccountService.service;

import com.Ironhack.AccountService.dto.accounts.*;
import com.Ironhack.AccountService.enums.Status;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

public interface IAccountService {
    AccountDTO createNewCheckingAccount(CheckingAccountDTO account);
    AccountDTO createNewSavingsAccount(SavingsAccountDTO account);
    AccountDTO createNewCreditCardAccount(CreditCardAccountDTO account);
    ThirdPartyAccountDTO createNewThirdPartyAccount(ThirdPartyAccountDTO account);
    Status updateStatus(long id);
}
