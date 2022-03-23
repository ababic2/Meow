package com.microservice.cat.controller;

import com.microservice.cat.entity.Account;
import com.microservice.cat.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/cat")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @GetMapping("/accounts") // done
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/accounts/{id}") // done
    public Account getAccounts(@PathVariable Long id) {
        return accountRepository.findById(id).get();
    }


    @PostMapping("/accounts") // done
    public Account createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @DeleteMapping("/accounts/{id}") // done
    public void deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
    }

    @PutMapping("/accounts/{id}") // done
    public void updateAccount(@PathVariable Long id ,@RequestBody Account account) {
        //Since account only holds a copy of a real account ID creating this makes no sense
    }

}
