package com.Ironhack.AccountService.proxy;

import com.Ironhack.AccountService.dao.ThirdPartyAccount;
import com.Ironhack.AccountService.dto.User;
import com.Ironhack.AccountService.dto.users.AccountHolderDTO;
import com.Ironhack.AccountService.dto.users.AdminDTO;
import com.Ironhack.AccountService.dto.users.ThirdPartyDTO;
import com.Ironhack.AccountService.dto.users.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@FeignClient("USER-SERVICE")
public interface UserRepositoryProxy extends JpaRepository<User, Long> {

    @GetMapping("/thirdParty/{username}")
    Optional<ThirdPartyAccount> findThirdPartyByUsername(@PathVariable(name="username") String username);

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers();

    @GetMapping("/users/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getAllUsersByUsername(@PathVariable(name = "username") String name);

    @GetMapping("/users/byid/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<UserDTO> getAllUsersById(@PathVariable(name = "id") Long id);

    @PostMapping("/users/new/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public AdminDTO createNewAdmin(@RequestBody @Valid AdminDTO admin);

    @PostMapping("/users/new/accountholder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolderDTO createNewAccountHolder(@RequestBody @Valid AccountHolderDTO accountHolder);

    @PostMapping("/users/new/thirdparty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdPartyDTO createNewThirdParty(@RequestBody @Valid ThirdPartyDTO thirdParty);

    @PatchMapping("/users/update/logindetails/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUsernameAndPassword(@PathVariable(name = "id") Long id,
                                          @RequestParam String username,
                                          @RequestParam String password);

    @PatchMapping("/users/update/username/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUsername(@PathVariable(name = "id") Long id,
                               @RequestParam String username);

    @PatchMapping("/users/update/password/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@PathVariable(name = "id") Long id,
                               @RequestParam String password);

    @PatchMapping("/users/update/accountholder/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountHolderDTO updateUser(@PathVariable(name = "id") Long id,
                                       @RequestBody @Valid AccountHolderDTO accountHolder);

    @PatchMapping("/users/update/thirdparty/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdPartyDTO updateUser(@PathVariable(name = "id") Long id,
                                    @RequestBody @Valid ThirdPartyDTO thirdParty);

    @PatchMapping("/users/update/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AdminDTO updateUser(@PathVariable(name = "id") Long id,
                               @RequestBody @Valid AdminDTO admin);

    @DeleteMapping("/users/remove/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void removeUser(@PathVariable(name = "id") Long id);
}
