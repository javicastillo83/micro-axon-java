package com.sbaxon.domain.bankservice.event;

import com.sbaxon.domain.bankservice.aggregate.BankServiceType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankServiceUpdatedEvent {

    private String bankServiceUUID;

    private String name;

    private BankServiceType bankServiceType;
}
