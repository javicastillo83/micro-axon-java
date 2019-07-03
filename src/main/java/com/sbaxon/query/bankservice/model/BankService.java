package com.sbaxon.query.bankservice.model;

import com.sbaxon.domain.bankservice.aggregate.BankServiceType;
import lombok.Data;

@Data
public class BankService {

    private String id;

    private String name;

    private BankServiceType type;
}
