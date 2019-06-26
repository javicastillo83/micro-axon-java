package com.sbaxon.business.client.controller;

import com.sbaxon.business.client.dto.CreateClientDTO;
import com.sbaxon.business.client.dto.SubscribeProductDTO;
import com.sbaxon.business.client.dto.UpdateClientDTO;
import com.sbaxon.business.client.service.IClientService;
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
        return clientService.create(createClientDTO);
    }

    @PutMapping("/{clientUUID}")
    public CompletableFuture<String> updateClient(@PathVariable String clientUUID, @RequestBody @Valid UpdateClientDTO updateClientDTO) {
        return this.clientService.update(clientUUID, updateClientDTO);
    }

    @PostMapping("/{clientUUID}/products")
    public CompletableFuture<String> subscribeProduct(@PathVariable String clientUUID, @RequestBody @Valid SubscribeProductDTO subscribeProductDTO) {
        return this.clientService.subscribeProduct(clientUUID, subscribeProductDTO);
    }

    @DeleteMapping("/{clientUUID}/products/{productUUID}")
    public CompletableFuture<String> unSubscribeProduct(@PathVariable String clientUUID, @PathVariable String productUUID) {
        return this.clientService.unSubscribeProduct(clientUUID,productUUID);
    }
}

