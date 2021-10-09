package com.Ironhack.AccountService.dao.usersubclasses;

import com.Ironhack.AccountService.dao.Account;
import com.Ironhack.AccountService.dao.Address;
import com.Ironhack.AccountService.dto.Role;
import com.Ironhack.AccountService.dto.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("ACCOUNTHOLDER")
public class AccountHolder extends User {

    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @NotNull
    @Column(name = "primary_address")
    @AttributeOverrides({
            @AttributeOverride( name = "houseNumber", column = @Column(name = "primary_house_number")),
            @AttributeOverride( name = "streetName", column = @Column(name = "primary_street_name")),
            @AttributeOverride( name = "city", column = @Column(name = "primary_city")),
            @AttributeOverride( name = "postcode", column = @Column(name = "primary_postcode")),
            @AttributeOverride( name = "country", column = @Column(name = "primary_country"))
    })
    private Address primaryAddress;

    @Column(name = "mailing_address")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "houseNumber", column = @Column(name = "mailing_house_number")),
            @AttributeOverride( name = "streetName", column = @Column(name = "mailing_street_name")),
            @AttributeOverride( name = "city", column = @Column(name = "mailing_city")),
            @AttributeOverride( name = "postcode", column = @Column(name = "mailing_postcode")),
            @AttributeOverride( name = "country", column = @Column(name = "mailing_country"))

    })
    private Address mailingAddress;

    @OneToMany(
            mappedBy = "primaryOwner",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Account> accounts;

    public AccountHolder(String name, String username, String password, LocalDate dateOfBirth, Address primaryAddress, Address mailingAddress, List<Account> accounts) {
        super(name, username, password);
        this.dateOfBirth = dateOfBirth;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
        this.accounts = accounts;
        addRole(new Role("ACCOUNTHOLDER",this));
    }

    public Boolean isUnder24() {
        LocalDate today = LocalDate.now();
        LocalDate flagValue
                = today.minus(24, ChronoUnit.YEARS);
        return this.getDateOfBirth().isAfter(flagValue);
    }
}
