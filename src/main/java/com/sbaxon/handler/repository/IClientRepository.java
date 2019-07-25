package com.sbaxon.handler.repository;

import com.sbaxon.handler.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClientRepository extends JpaRepository<Client, Long> {

    Client findByUuid(String uuid);

    void removeByUuid(String clientUUID);
}
