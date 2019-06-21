package com.sbaxon.messaging.client.repository;

import com.sbaxon.messaging.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client,Long> {

    Client findByUuid(String uuid);

    void deleteByUuid(String uuid);
}
