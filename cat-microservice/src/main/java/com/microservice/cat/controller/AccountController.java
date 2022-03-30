package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.exceptions.CatResponse;
import com.microservice.cat.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/api/cat")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts") // done
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}") // done
    public CatResponse getAccounts(@PathVariable Long id) {
        try {
            Account findAccount = accountRepository.findById(id).get();
            return CatResponse.ok().setPayload(findAccount);
        }catch (Exception e){
            return CatResponse.notFound().setErrors(String.format("User with id "+id+" was not found"));
        }
    }


    @PostMapping("/accounts") // done
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @DeleteMapping("/accounts/{id}") // done
    public CatResponse deleteAccount(@PathVariable Long id) {
        try {
            accountRepository.deleteById(id);
            return CatResponse.ok().setMetadata("Account deleted");
        }catch(Exception e){
            return CatResponse.notFound().setErrors(String.format("Error deleting user "+e.getMessage()));
        }
    }

    @PutMapping("/accounts/{id}") // done
    public void updateAccount(@PathVariable Long id ,@RequestBody Account account) {
        //Since account only holds a copy of a real account ID creating this makes no sense
    }

}
