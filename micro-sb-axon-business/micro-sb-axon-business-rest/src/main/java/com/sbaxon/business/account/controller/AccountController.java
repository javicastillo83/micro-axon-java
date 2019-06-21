package com.sbaxon.business.account.controller;

import com.sbaxon.business.account.dto.AccountDTO;
import com.sbaxon.business.account.service.IAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity createAccount(@RequestBody @Valid AccountDTO accountDTO) {
        accountService.create(accountDTO);
        return ResponseEntity.accepted()
                             .body(AccountDTO.builder()
                                             .uuid(accountDTO.getUuid())
                                             .build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity updateAccount(@PathVariable String uuid, @RequestBody @Valid AccountDTO accountDTO) {
        this.accountService.update(uuid, accountDTO);
        return ResponseEntity.accepted()
                             .build();
    }

    @PutMapping("/{uuid}/credit/{amount}")
    public ResponseEntity credit(@PathVariable String uuid, @PathVariable double amount) {
        this.accountService.credit(uuid, amount);
        return ResponseEntity.accepted()
                             .build();
    }

    @PutMapping("/{uuid}/debit/{amount}")
    public ResponseEntity debit(@PathVariable String uuid, @PathVariable double amount) {
        this.accountService.debit(uuid, amount);
        return ResponseEntity.accepted()
                             .build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteAccount(@PathVariable String uuid) {
        this.accountService.delete(uuid);
        return ResponseEntity.accepted()
                             .build();
    }

    @PostMapping("/sync")
    public ResponseEntity<AccountDTO> syncCreateAccount(@RequestBody @Valid AccountDTO accountDTO) {
        return new ResponseEntity(this.accountService.createSync(accountDTO), HttpStatus.OK);
    }

    @PutMapping("/sync/{uuid}")
    public ResponseEntity<AccountDTO> syncUpdateAccount(@PathVariable String uuid, @RequestBody @Valid AccountDTO accountDTO) {
        return new ResponseEntity(this.accountService.updateSync(uuid, accountDTO), HttpStatus.OK);
    }

    @DeleteMapping("/sync/{uuid}")
    public ResponseEntity<String> syncDeleteAccount(@PathVariable String uuid) {
        this.accountService.deleteSync(uuid);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PutMapping("/sync/{uuid}/credit/{amount}")
    public ResponseEntity<AccountDTO> creditSync(@PathVariable String uuid, @PathVariable double amount) {
        return new ResponseEntity(this.accountService.creditSync(uuid, amount),HttpStatus.OK);
    }

    @PutMapping("/sync/{uuid}/debit/{amount}")
    public ResponseEntity<AccountDTO>  debitSync(@PathVariable String uuid, @PathVariable double amount) {
        return new ResponseEntity(this.accountService.debitSync(uuid, amount),HttpStatus.OK);

    }

}

