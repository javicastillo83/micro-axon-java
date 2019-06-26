package com.sbaxon.business.client.aggregate;

import lombok.Data;
import org.axonframework.modelling.command.EntityId;

@Data
public class ProductEntity {

    @EntityId
    private String productUUID;

    private String serviceUUID;
}
