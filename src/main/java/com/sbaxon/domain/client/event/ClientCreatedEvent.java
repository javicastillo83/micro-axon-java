package com.sbaxon.domain.client.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientCreatedEvent {

    private String clientUUID;

    private String firstName;

    private String lastName;

    private String email;
}
