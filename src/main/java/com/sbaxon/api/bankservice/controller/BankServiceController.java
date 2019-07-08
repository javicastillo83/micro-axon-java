package com.sbaxon.api.bankservice.controller;

import com.sbaxon.api.bankservice.dto.CreateBankServiceDTO;
import com.sbaxon.domain.bankservice.service.CreateBankService;
import com.sbaxon.domain.bankservice.service.IBankServiceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bankservices")
public class BankServiceController {

    private final IBankServiceService serviceService;

    public BankServiceController(IBankServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public CompletableFuture<String> create(@RequestBody @Valid CreateBankServiceDTO createBankServiceDTO) {
        return serviceService.create(CreateBankService.builder()
                                                      .bankServiceType(createBankServiceDTO.getBankServiceType())
                                                      .name(createBankServiceDTO.getName())
                                                      .build());
    }

}

