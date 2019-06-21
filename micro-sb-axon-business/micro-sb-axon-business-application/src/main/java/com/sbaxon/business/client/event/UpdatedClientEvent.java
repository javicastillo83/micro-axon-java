package com.sbaxon.business.client.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatedClientEvent {

   private String uuid;

   private String name;
}
