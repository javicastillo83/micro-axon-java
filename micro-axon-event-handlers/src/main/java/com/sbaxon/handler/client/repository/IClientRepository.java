package com.sbaxon.handler.client.repository;

import com.sbaxon.handler.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client,Long> {

    Client findByUuid(String uuid);

}
