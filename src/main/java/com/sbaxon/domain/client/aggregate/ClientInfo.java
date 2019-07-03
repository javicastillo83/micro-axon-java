package com.sbaxon.domain.client.aggregate;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ClientInfo  implements Serializable {

    private String firstName;

    private String lastName;

    private String email;
}
