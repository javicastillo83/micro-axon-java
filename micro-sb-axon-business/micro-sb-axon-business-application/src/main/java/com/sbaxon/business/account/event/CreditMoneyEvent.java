package com.sbaxon.business.account.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreditMoneyEvent {

   private String uuid;
   private double amount;
}
