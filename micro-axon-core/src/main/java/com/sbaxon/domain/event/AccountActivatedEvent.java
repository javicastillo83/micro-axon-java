package com.sbaxon.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountActivatedEvent {

    private String accountUUID;

    private AccountStatus status;
}
