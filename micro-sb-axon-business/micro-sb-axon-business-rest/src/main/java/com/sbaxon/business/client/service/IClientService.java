package com.sbaxon.business.client.service;

import com.sbaxon.business.client.dto.ClientDTO;

import java.util.concurrent.CompletableFuture;

public interface IClientService {

    CompletableFuture<ClientDTO> create(ClientDTO clientDTO);
    CompletableFuture<ClientDTO> update(String uuid, ClientDTO clientDTO);
    CompletableFuture<Void> delete(String uuid);

    ClientDTO createSync(ClientDTO clientDTO);
    ClientDTO updateSync(String uuid, ClientDTO clientDTO);
    void deleteSync(String uuid);

}
