package com.sbaxon.domain.account.aggregate;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.EntityId;

import java.util.Date;

@Data
@Builder
public class Operation {

    @EntityId
    private String operationUUID;

    private Date date;

    private double amount;


}
