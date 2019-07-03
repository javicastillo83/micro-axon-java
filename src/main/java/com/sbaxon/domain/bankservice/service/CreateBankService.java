package com.sbaxon.domain.bankservice.service;

import com.sbaxon.domain.bankservice.aggregate.BankServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBankService {

    private String name;

    private BankServiceType bankServiceType;

}
