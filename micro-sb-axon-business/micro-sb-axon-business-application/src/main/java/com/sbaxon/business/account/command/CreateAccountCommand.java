package com.sbaxon.business.account.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateAccountCommand {

   @TargetAggregateIdentifier
   private String uuid;

   private String name;
}
