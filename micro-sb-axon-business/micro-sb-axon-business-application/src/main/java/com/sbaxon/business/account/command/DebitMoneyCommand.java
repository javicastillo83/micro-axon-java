package com.sbaxon.business.account.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DebitMoneyCommand {

   @TargetAggregateIdentifier
   private String accountUUID;

   private double amount;
}
