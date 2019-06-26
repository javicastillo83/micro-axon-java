package com.sbaxon.messaging.service.repository;

import com.sbaxon.messaging.service.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceRepository extends JpaRepository<Service,Long> {

    Service findByUuid(String uuid);

}
