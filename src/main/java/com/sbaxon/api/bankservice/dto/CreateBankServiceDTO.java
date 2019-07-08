package com.sbaxon.api.bankservice.dto;

import com.sbaxon.domain.bankservice.aggregate.BankServiceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBankServiceDTO {

    private String name;

    private BankServiceType bankServiceType;

}
