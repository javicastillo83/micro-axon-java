package com.sbaxon.handler.repository;

import com.sbaxon.handler.entity.BankService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankServiceRepository extends JpaRepository<BankService,Long> {

    BankService findByUuid(String uuid);

    void removeByUuid(String uuid);
}
