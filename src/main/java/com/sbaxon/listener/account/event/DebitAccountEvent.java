package com.sbaxon.listener.account.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebitAccountEvent {

    private String accountUUID;
    private double amount;


}
