package com.sbaxon.domain.account.event;

import com.sbaxon.domain.account.aggregate.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDisabledEvent {

    private String accountUUID;

    private AccountStatus status;
}
