package com.sbaxon.api.client.controller;

import com.sbaxon.api.client.dto.CreateClientDTO;
import com.sbaxon.api.client.dto.SubscribeProductDTO;
import com.sbaxon.api.client.dto.UpdateClientDTO;
import com.sbaxon.domain.client.service.CreateClient;
import com.sbaxon.domain.client.service.IClientService;
import com.sbaxon.domain.client.service.SubscribeProduct;
import com.sbaxon.domain.client.service.UpdateClient;
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

    @DeleteMapping("/{clientUUID}")
    public CompletableFuture<String> deleteClient(@PathVariable String clientUUID) {
        return this.clientService.remove(clientUUID);
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

