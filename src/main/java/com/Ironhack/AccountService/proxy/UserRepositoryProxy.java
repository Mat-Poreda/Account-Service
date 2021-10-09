package com.Ironhack.AccountService.proxy;

import com.Ironhack.AccountService.dao.ThirdPartyAccount;
import com.Ironhack.AccountService.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient("USER-SERVICE")
public interface UserRepositoryProxy extends JpaRepository<User, Long> {
    @GetMapping("/users/{username}")
    Optional<User> findByUsername(@PathVariable(name="username") String username);

    @GetMapping("/thirdParty/{username}")
    Optional<ThirdPartyAccount> findThirdPartyByUsername(@PathVariable(name="username") String username);


}
