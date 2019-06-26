package com.sbaxon.business.service.event;

import com.sbaxon.business.service.aggregate.ServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceCreatedEvent {

    private String serviceUUID;

    private String name;

    private ServiceType serviceType;
}
