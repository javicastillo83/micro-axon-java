package com.sbaxon.business.client.controller;

import com.sbaxon.business.client.dto.ClientDTO;
import com.sbaxon.business.client.service.IClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity createClient(@RequestBody @Valid ClientDTO clientDTO) {
        clientService.create(clientDTO);
        return ResponseEntity.accepted()
                             .body(ClientDTO.builder()
                                             .uuid(clientDTO.getUuid())
                                             .build());
    }

    @PutMapping("/{uuid}")
    public ResponseEntity updateClient(@PathVariable String uuid, @RequestBody @Valid ClientDTO clientDTO) {
        this.clientService.update(uuid, clientDTO);
        return ResponseEntity.accepted()
                             .build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity deleteClient(@PathVariable String uuid) {
        this.clientService.delete(uuid);
        return ResponseEntity.accepted()
                             .build();
    }

    @PostMapping("/sync")
    public ResponseEntity<ClientDTO> syncCreateClient(@RequestBody @Valid ClientDTO clientDTO) {
        return new ResponseEntity(this.clientService.createSync(clientDTO), HttpStatus.OK);
    }

    @PutMapping("/sync/{uuid}")
    public ResponseEntity<ClientDTO> syncUpdateClient(@PathVariable String uuid, @RequestBody @Valid ClientDTO clientDTO) {
        return new ResponseEntity(this.clientService.updateSync(uuid, clientDTO), HttpStatus.OK);
    }

    @DeleteMapping("/sync/{uuid}")
    public ResponseEntity<String> syncDeleteClient(@PathVariable String uuid) {
        this.clientService.deleteSync(uuid);
        return new ResponseEntity(HttpStatus.OK);

    }

}

