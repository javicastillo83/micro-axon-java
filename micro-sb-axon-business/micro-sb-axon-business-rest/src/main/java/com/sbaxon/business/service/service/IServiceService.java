package com.sbaxon.business.service.service;

import com.sbaxon.business.service.dto.CreateServiceDTO;

import java.util.concurrent.CompletableFuture;

public interface IServiceService {

    CompletableFuture<String> create(CreateServiceDTO createServiceDTO);
}
