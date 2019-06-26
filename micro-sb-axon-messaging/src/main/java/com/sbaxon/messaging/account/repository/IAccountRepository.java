package com.sbaxon.messaging.account.repository;

import com.sbaxon.messaging.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepository extends JpaRepository<Account,Long> {

    Account findByUuid(String uuid);

}
