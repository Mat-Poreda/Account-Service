package com.Ironhack.AccountService.dto.accounts;

import com.Ironhack.AccountService.dao.Money;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    protected long id;

    @NotNull
    protected Money balance;

    @NotNull
    protected Long primaryOwnerId;

    protected Long secondaryOwnerId;

    public AccountDTO(Money balance, Long primaryOwnerId, Long secondaryOwnerId) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
    }

    public AccountDTO(Money balance, Long primaryOwnerId) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
    }
}
