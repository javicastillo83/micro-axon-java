package com.sbaxon.business.service.command;

import com.sbaxon.business.service.aggregate.ServiceType;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateServiceCommand {

    @TargetAggregateIdentifier
    private String serviceUUID;

    private String name;

    private ServiceType serviceType;
}
