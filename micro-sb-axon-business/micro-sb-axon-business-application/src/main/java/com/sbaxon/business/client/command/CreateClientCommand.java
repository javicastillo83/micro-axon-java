package com.sbaxon.business.client.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateClientCommand {

   @TargetAggregateIdentifier
   private String uuid;

   private String name;
}