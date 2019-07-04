package com.sbaxon.api.controller;

import com.sbaxon.domain.dto.CreateClientDTO;
import com.sbaxon.domain.dto.SubscribeProductDTO;
import com.sbaxon.domain.dto.UpdateClientDTO;
import com.sbaxon.domain.service.CreateClient;
import com.sbaxon.domain.service.IClientService;
import com.sbaxon.domain.service.SubscribeProduct;
import com.sbaxon.domain.service.UpdateClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public CompletableFuture<String> createClient(@RequestBody @Valid CreateClientDTO createClientDTO) {
        return clientService.create(CreateClient.builder()
                                                .email(createClientDTO.getEmail())
                                                .firstName(createClientDTO.getFirstName())
                                                .lastName(createClientDTO.getLastName())
                                                .build());
    }

    @PutMapping("/{clientUUID}")
    public CompletableFuture<String> updateClient(@PathVariable String clientUUID, @RequestBody @Valid UpdateClientDTO updateClientDTO) {
        return this.clientService.update(clientUUID, UpdateClient.builder()
                                                                 .email(updateClientDTO.getEmail())
                                                                 .firstName(updateClientDTO.getFirstName())
                                                                 .lastName(updateClientDTO.getLastName())
                                                                 .build());
    }

    @PostMapping("/{clientUUID}/products")
    public CompletableFuture<String> subscribeProduct(@PathVariable String clientUUID, @RequestBody @Valid SubscribeProductDTO subscribeProductDTO) {
        return this.clientService.subscribeProduct(clientUUID, SubscribeProduct.builder()
                                                                               .bankServiceUUID(subscribeProductDTO.getBankServiceUUID())
                                                                               .build());
    }

    @DeleteMapping("/{clientUUID}/products/{productUUID}")
    public CompletableFuture<String> unSubscribeProduct(@PathVariable String clientUUID, @PathVariable String productUUID) {
        return this.clientService.unSubscribeProduct(clientUUID, productUUID);
    }
}

