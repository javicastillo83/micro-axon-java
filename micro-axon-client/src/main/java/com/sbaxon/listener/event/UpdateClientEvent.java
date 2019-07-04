package com.sbaxon.listener.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientEvent {

    private String clientUUID;

    private String firstName;

    private String lastName;

    private String email;

}
