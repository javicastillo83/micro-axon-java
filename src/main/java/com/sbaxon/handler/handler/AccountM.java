package com.sbaxon.handler.handler;

import com.sbaxon.handler.entity.AccountStatus;
import lombok.Data;

@Data
public class AccountM {

    private Long id;

    private String uuid;

    private String number;

    private double balance;

    private AccountStatus status;
}
