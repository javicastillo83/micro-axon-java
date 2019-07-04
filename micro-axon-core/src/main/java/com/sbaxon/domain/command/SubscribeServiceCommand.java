package com.sbaxon.domain.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class SubscribeServiceCommand {

    @TargetAggregateIdentifier
    private String clientUUID;

    private String bankServiceUUID;

}
