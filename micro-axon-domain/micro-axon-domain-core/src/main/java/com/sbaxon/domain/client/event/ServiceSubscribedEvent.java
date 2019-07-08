package com.sbaxon.domain.client.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceSubscribedEvent {

    private String subscriptionUUID;

    private String bankServiceUUID;

    private String clientUUID;


}
