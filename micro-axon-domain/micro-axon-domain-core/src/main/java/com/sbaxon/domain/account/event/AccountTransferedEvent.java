package com.sbaxon.domain.account.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTransferedEvent {

    private String accountUUID;

    private double amount;

    private String description;

}
