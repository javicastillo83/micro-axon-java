package com.sbaxon.handler.account.repository;

import com.sbaxon.handler.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Long> {

    Account findByUuid(String uuid);

}
