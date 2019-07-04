package com.sbaxon.listener.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnSubscribeProductEvent {

    private String clientUUID;
    private String bankServiceUUID;

}
