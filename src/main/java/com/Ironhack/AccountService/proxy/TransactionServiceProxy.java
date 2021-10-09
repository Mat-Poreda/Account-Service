package com.Ironhack.AccountService.proxy;

import com.Ironhack.AccountService.dto.MoneyDTO;
import com.Ironhack.AccountService.dto.accounts.AccountDTO;
import com.Ironhack.AccountService.dto.accounts.ThirdPartyAccountDTO;
import com.Ironhack.AccountService.dto.transactions.ThirdPartyTransactionDTO;
import com.Ironhack.AccountService.dto.transactions.TransactionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@FeignClient("TRANSACTION-SERVICE")
public interface TransactionServiceProxy {

    @GetMapping("/accounts/getbalance/checking/{account_id}")
    @ResponseStatus(HttpStatus.OK)
    MoneyDTO getCheckingBalance(@PathVariable(name = "account_id") long id, @RequestParam String username);

    @GetMapping("/accounts/getbalance/studentchecking/{account_id}")
    @ResponseStatus(HttpStatus.OK)
     MoneyDTO getStudentCheckingBalance(@PathVariable(name = "account_id") long id, @RequestParam String username);

    @GetMapping("/accounts/getbalance/savings/{account_id}")
    @ResponseStatus(HttpStatus.OK)
    MoneyDTO getSavingsBalance(@PathVariable(name = "account_id") long id, @RequestParam String username);

    @GetMapping("/accounts/getbalance/creditcard/{account_id}")
    @ResponseStatus(HttpStatus.OK)
     MoneyDTO getCreditCardBalance(@PathVariable(name = "account_id") long id, @RequestParam String username);

    @GetMapping("/accounts/getbalance/thirdparty/{account_id}")
    @ResponseStatus(HttpStatus.OK)
    public MoneyDTO getThirdPartyBalance(@PathVariable(name = "account_id") long id, @RequestParam String username);

    @PatchMapping("/accounts/admin/transferfunds/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountDTO adminTransferFunds(@RequestBody @Valid TransactionDTO transaction) ;
    @PatchMapping("/accounts/accountholder/transferfunds/{username}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AccountDTO accHolderTransferFunds(@PathVariable(name = "username") String username,
                                             @RequestBody @Valid TransactionDTO transaction) ;

    @PatchMapping("/accounts/thirdparty/transferfunds/")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ThirdPartyAccountDTO thirdPartyTransferFunds(@RequestParam String hashedKey,
                                                        @RequestBody @Valid ThirdPartyTransactionDTO transaction);
}
