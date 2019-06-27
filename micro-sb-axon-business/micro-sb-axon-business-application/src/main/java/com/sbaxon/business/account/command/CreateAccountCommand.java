package com.sbaxon.business.account.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateAccountCommand {

    @TargetAggregateIdentifier
    private String accountUUID;

    private String clientUUID;

    private String productUUID;

    private String subscriptionUUID;
}
