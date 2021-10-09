package com.Ironhack.AccountService.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;


import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roleType;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private SecurityProperties.User user;

    public Role(String roleType) {
        this.roleType = roleType;
    }

    public Role(String roleType, SecurityProperties.User user) {
        this.roleType = roleType;
        this.user = user;
    }

    @Override
    public String toString() {
        return this.roleType;
    }
}
