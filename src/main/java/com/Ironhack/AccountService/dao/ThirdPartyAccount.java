package com.Ironhack.AccountService.dao;

import com.Ironhack.AccountService.dao.usersubclasses.ThirdParty;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Currency;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ThirdPartyAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "amount", column = @Column(name = "balance")),
            @AttributeOverride( name = "currency", column = @Column(name = "balance_currency"))
    })
    protected Money balance;

    @ManyToOne
    @JoinColumn(name = "third_party_primary_owner_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected ThirdParty primaryOwner;


    @ManyToOne
    @JoinColumn(name = "third_party_secondary_owner_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    protected ThirdParty secondaryOwner;

    @NotNull
    @Column(name = "penalty_fee")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "amount", column = @Column(name = "penalty_fee")),
            @AttributeOverride( name = "currency", column = @Column(name = "penalty_fee_currency"))
    })
    protected Money penaltyFee;

    @NotNull
    protected LocalDate creationDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    protected Status status;

    @OneToMany(
            mappedBy = "thirdPartyTransferAccount",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    protected List<ThirdPartyTransaction> paymentTransactions;

    @OneToMany(
            mappedBy = "receivingAccount",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    protected List<Transaction> receivingTransactions;

    @NotNull
    private String hashedKey;

    @NotNull
    private String name;

    public ThirdPartyAccount(Money balance, ThirdParty primaryOwner, String hashedKey, String name) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.penaltyFee = new Money(Constants.PENALTY_FEE, Currency.getInstance("GBP"));
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
        this.paymentTransactions = new ArrayList<>();
        this.receivingTransactions = new ArrayList<>();
        this.hashedKey = hashedKey;
        this.name = name;
    }

    public ThirdPartyAccount(Money balance, ThirdParty primaryOwner, ThirdParty secondaryOwner, String hashedKey, String name) {
        this.balance = balance;
        this.primaryOwner = primaryOwner;
        this.secondaryOwner = secondaryOwner;
        this.penaltyFee = new Money(Constants.PENALTY_FEE, Currency.getInstance("GBP"));
        this.creationDate = LocalDate.now();
        this.status = Status.ACTIVE;
        this.paymentTransactions = new ArrayList<>();
        this.receivingTransactions = new ArrayList<>();
        this.hashedKey = hashedKey;
        this.name = name;
    }
}
