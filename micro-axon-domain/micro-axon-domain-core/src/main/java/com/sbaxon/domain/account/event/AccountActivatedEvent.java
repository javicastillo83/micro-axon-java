package com.sbaxon.domain.account.event;

import com.sbaxon.domain.account.aggregate.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountActivatedEvent {

    private String accountUUID;

    private AccountStatus status;
}
