package com.Ironhack.AccountService.dao.usersubclasses;


import com.Ironhack.AccountService.dto.Role;
import com.Ironhack.AccountService.dto.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@Entity
@DiscriminatorValue("ADMIN")
public class Admin extends User {

    public Admin(String name, String username, String password) {
        super(name, username, password);
        addRole(new Role("ADMIN",this));
    }
}
