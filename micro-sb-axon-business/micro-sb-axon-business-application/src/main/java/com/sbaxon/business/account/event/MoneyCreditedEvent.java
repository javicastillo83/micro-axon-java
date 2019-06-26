package com.sbaxon.business.account.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoneyCreditedEvent {

    private String accountUUID;

    private String clientUUID;

    private Date date;

    private double amount;

}
