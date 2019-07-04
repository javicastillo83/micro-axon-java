package com.sbaxon.domain.aggregate;

import lombok.Data;
import org.axonframework.modelling.command.EntityId;

import java.io.Serializable;

@Data
public class ProductEntity  implements Serializable {

    @EntityId
    private String productUUID;

    private String bankServiceUUID;
}
