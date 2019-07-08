package com.sbaxon.domain.account.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoneyDebitedEvent {

    private String accountUUID;

    private String clientUUID;

    private Date date;

    private double amount;


}
