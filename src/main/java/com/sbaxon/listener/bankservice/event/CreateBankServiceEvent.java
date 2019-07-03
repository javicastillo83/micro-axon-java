package com.sbaxon.listener.bankservice.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBankServiceEvent {

    private String name;

    private String bankServiceType;

}
