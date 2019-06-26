package com.sbaxon.business.client.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class UnSubscribeServiceCommand {

    @TargetAggregateIdentifier
    private String clientUUID;

    private String productUUID;

}
