package com.sbaxon.domain.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateClientCommand {

    @TargetAggregateIdentifier
    private String clientUUID;

    private String firstName;

    private String lastName;

    private String email;
}
