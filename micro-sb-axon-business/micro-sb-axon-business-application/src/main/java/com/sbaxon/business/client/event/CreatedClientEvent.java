package com.sbaxon.business.client.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreatedClientEvent {

   private String uuid;
   private String name;
}
