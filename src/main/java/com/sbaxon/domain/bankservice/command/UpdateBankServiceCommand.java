package com.sbaxon.domain.bankservice.command;

import com.sbaxon.domain.bankservice.aggregate.BankServiceType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UpdateBankServiceCommand {

    @TargetAggregateIdentifier
    private String bankServiceUUID;

    private String name;

    private BankServiceType bankServiceType;
}
