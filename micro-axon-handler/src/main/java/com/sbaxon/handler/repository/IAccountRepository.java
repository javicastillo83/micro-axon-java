package com.sbaxon.handler.repository;

import com.sbaxon.handler.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Long> {

    Account findByUuid(String uuid);

}
