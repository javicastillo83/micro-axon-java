package com.sbaxon.domain.account.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountTransferedEvent {

    private String accountUUID;

    private double amount;

    private String description;

}
