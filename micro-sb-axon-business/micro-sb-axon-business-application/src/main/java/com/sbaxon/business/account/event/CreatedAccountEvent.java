package com.sbaxon.business.account.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatedAccountEvent {

   private String uuid;
   private String name;
}
