package com.sbaxon.business.client.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceSubscribedEvent {

    private String subscriptionUUID;

    private String serviceUUID;

    private String clientUUID;


}
