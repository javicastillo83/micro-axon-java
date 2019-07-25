package com.sbaxon.api.bankservice.controller;

import com.sbaxon.api.bankservice.dto.CreateBankServiceDTO;
import com.sbaxon.api.bankservice.dto.UpdateBankServiceDTO;
import com.sbaxon.domain.bankservice.service.CreateBankService;
import com.sbaxon.domain.bankservice.service.IBankServiceService;
import com.sbaxon.domain.bankservice.service.UpdateBankService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bankservices")
public class BankServiceController {

    private final IBankServiceService bankServiceService;

    public BankServiceController(IBankServiceService bankServiceService) {
        this.bankServiceService = bankServiceService;
    }

    @PostMapping
    public CompletableFuture<String> create(@RequestBody @Valid CreateBankServiceDTO createBankServiceDTO) {
        return bankServiceService.create(CreateBankService.builder()
                                                          .bankServiceType(createBankServiceDTO.getBankServiceType())
                                                          .name(createBankServiceDTO.getName())
                                                          .build());
    }

    @PutMapping("/{bankServiceUUID}")
    public CompletableFuture<String> updateBankService(@PathVariable String bankServiceUUID, @RequestBody @Valid UpdateBankServiceDTO updateBankServiceDTO) {
        return this.bankServiceService.update(bankServiceUUID, UpdateBankService.builder()
                                                                                .name(updateBankServiceDTO.getName())
                                                                                .bankServiceType(updateBankServiceDTO.getBankServiceType())
                                                                                .build());
    }

    @DeleteMapping("/{bankServiceUUID}")
    public CompletableFuture<String> deleteBankService(@PathVariable String bankServiceUUID) {
        return this.bankServiceService.remove(bankServiceUUID);
    }
}

