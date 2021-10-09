package com.Ironhack.AccountService.dto.accounts;

import com.Ironhack.AccountService.dao.Money;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyAccountDTO {

    private long id;

    @NotNull
    private Money balance;

    @NotNull
    private Long primaryOwnerId;

    private Long secondaryOwnerId;

    @NotBlank
    private String hashedKey;

    @NotNull
    private String name;

    public ThirdPartyAccountDTO(Money balance, Long primaryOwnerId, String hashedKey, String name) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.hashedKey = hashedKey;
        this.name = name;
    }

    public ThirdPartyAccountDTO(Money balance, Long primaryOwnerId, Long secondaryOwnerId, String hashedKey, String name) {
        this.balance = balance;
        this.primaryOwnerId = primaryOwnerId;
        this.secondaryOwnerId = secondaryOwnerId;
        this.hashedKey = hashedKey;
        this.name = name;
    }
}
