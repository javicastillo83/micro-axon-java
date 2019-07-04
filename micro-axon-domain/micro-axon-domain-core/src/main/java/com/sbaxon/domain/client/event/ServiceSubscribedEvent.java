package com.sbaxon.domain.client.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceSubscribedEvent {

    private String subscriptionUUID;

    private String bankServiceUUID;

    private String clientUUID;


}
