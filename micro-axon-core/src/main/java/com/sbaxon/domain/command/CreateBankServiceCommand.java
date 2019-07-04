package com.sbaxon.domain.command;

import com.sbaxon.domain.event.BankServiceType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateBankServiceCommand {

    @TargetAggregateIdentifier
    private String bankServiceUUID;

    private String name;

    private BankServiceType bankServiceType;
}
