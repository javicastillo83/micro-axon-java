package com.sbaxon.domain.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ServiceUnSubscribedEvent {

    private String productUUID;


}
