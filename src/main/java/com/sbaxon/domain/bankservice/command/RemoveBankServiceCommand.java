package com.sbaxon.domain.bankservice.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class RemoveBankServiceCommand {

    @TargetAggregateIdentifier
    private String bankServiceUUID;
}
