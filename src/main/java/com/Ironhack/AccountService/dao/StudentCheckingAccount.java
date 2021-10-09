package com.Ironhack.AccountService.dao;

import com.ironhack.midterm.dao.users.usersubclasses.AccountHolder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("STUDENTCHECKING")
public class StudentCheckingAccount extends Account {

    @NotBlank
    @Column(name = "secret_key")
    private String secretKey;

    public StudentCheckingAccount(Money balance, AccountHolder primaryOwner, String secretKey) {
        super(balance);
        this.primaryOwner = primaryOwner;
        this.secretKey = secretKey;
    }

    public StudentCheckingAccount(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, String secretKey) {
        super(balance);
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.secretKey = secretKey;
    }
}
