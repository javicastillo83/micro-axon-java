package com.sbaxon.domain.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreditMoneyCommand {

   @TargetAggregateIdentifier
   private String accountUUID;

   private double amount;

   private String description;

}
