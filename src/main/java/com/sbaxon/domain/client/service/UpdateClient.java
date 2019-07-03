package com.sbaxon.domain.client.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClient {

    private String firstName;

    private String lastName;

    private String email;

}
