package com.sbaxon.domain.event;

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
