package com.Ironhack.AccountService.dto.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ThirdPartyDTO {

    private long id;

    @NotNull
    private String name;

    private String username;
    private String password;

    @NotBlank
    private String hashedKey;

    public ThirdPartyDTO(String name, String username, String password, String hashedKey) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.hashedKey = hashedKey;
    }
}
