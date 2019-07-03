package com.sbaxon.query.account.model;

import com.sbaxon.query.client.model.Client;
import lombok.Data;

@Data
public class Account {

    private String id;

    private String number;

    private String status;

    private double balance;

    private Client client;

}
