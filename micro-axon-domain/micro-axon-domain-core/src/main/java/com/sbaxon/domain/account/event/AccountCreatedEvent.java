package com.sbaxon.domain.account.event;

import com.sbaxon.domain.account.aggregate.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountCreatedEvent {

   private String subscriptionUUID;
   private String accountUUID;
   private String clientUUID;
   private String number;
   private AccountStatus status;
   private double balance;


}
