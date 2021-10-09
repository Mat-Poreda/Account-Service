package com.Ironhack.AccountService.dao.usersubclasses;

import com.Ironhack.AccountService.dao.ThirdPartyAccount;
import com.Ironhack.AccountService.dto.Role;
import com.Ironhack.AccountService.dto.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("THIRDPARTY")
public class ThirdParty extends User {

    @NotNull
    @Column(name = "hashed_key")
    private String hashedKey;

    @OneToMany(
            mappedBy = "primaryOwner",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<ThirdPartyAccount> accounts;

    public ThirdParty(String name, String username, String password, String hashedKey) {
        super(name, username, password);
        this.hashedKey = hashedKey;
        addRole(new Role("THIRDPARTY",this));
    }
}
