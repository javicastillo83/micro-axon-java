package com.sbaxon.domain.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientUpdatedEvent {

    private String clientUUID;

    private String firstName;

    private String lastName;

    private String email;
}