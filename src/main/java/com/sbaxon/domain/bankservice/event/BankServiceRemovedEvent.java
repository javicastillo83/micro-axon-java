package com.sbaxon.domain.bankservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankServiceRemovedEvent {

    private String bankServiceUUID;

}
