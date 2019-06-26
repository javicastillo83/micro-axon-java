package com.sbaxon.business.service.controller;

import com.sbaxon.business.service.dto.CreateServiceDTO;
import com.sbaxon.business.service.service.IServiceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/services")
public class ServiceController {

    private final IServiceService serviceService;

    public ServiceController(IServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @PostMapping
    public CompletableFuture<String> create(@RequestBody @Valid CreateServiceDTO createServiceDTO) {
        return serviceService.create(createServiceDTO);
    }

}

