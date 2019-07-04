package com.sbaxon.handler.bankservice.repository;

import com.sbaxon.handler.bankservice.entity.BankService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBankServiceRepository extends JpaRepository<BankService,Long> {

    BankService findByUuid(String uuid);

}
