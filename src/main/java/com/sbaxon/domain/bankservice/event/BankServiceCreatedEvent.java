package com.sbaxon.domain.bankservice.event;

import com.sbaxon.domain.bankservice.aggregate.BankServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankServiceCreatedEvent {

    private String bankServiceUUID;

    private String name;

    private BankServiceType bankServiceType;
}
