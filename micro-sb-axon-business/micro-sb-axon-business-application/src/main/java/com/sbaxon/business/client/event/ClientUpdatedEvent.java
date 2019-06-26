package com.sbaxon.business.client.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientUpdatedEvent {

    private String clientUUID;

    private String firstName;

    private String lastName;

    private String email;
}
