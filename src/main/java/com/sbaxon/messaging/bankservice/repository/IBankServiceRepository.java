package com.sbaxon.messaging.bankservice.repository;

import com.sbaxon.messaging.bankservice.entity.BankService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankServiceRepository extends JpaRepository<BankService,Long> {

    BankService findByUuid(String uuid);

}
